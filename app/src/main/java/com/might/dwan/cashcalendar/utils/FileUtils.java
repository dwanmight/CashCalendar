package com.might.dwan.cashcalendar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Might on 23.09.2017.
 */

public class FileUtils {
    private WeakReference<Context> mWeakContext;

    public FileUtils(Context context) {
        mWeakContext = new WeakReference<Context>(context);
    }

    public String createCameraFile() {
        File file = mWeakContext.get().getExternalFilesDir(Environment.DIRECTORY_PICTURES + File.separator + "PICTURES");
        if (!file.exists()) {
            file.mkdirs();
        }

        String name_postfix = String.valueOf(System.currentTimeMillis());
        String name_prefix = "camera_";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name_prefix).append(name_postfix);
        File cameraFile = null;
        try {
            cameraFile = File.createTempFile(stringBuffer.toString(), ".jpg", file);
            ContentValues cv = new ContentValues();
            cv.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            cv.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            cv.put(MediaStore.MediaColumns.DATA, cameraFile.getAbsolutePath());
            mWeakContext.get().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cameraFile == null ? null : getFilePath(cameraFile);
    }

    public String getFilePath(File file) {
        String res;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            Uri photoURI = FileProvider.getUriForFile(mWeakContext.get(),
                    "com.might.dwan.cashcalendar.fileprovider",
                    file);
            res = photoURI.toString();
        } else {
            res = String.valueOf(Uri.fromFile(file));
        }
        return res;
    }
}
