package com.might.dwan.cashcalendar.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
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
        File file = new File(mWeakContext.get().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "CameraFile");
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
