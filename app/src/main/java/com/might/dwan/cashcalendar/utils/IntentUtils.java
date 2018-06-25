package com.might.dwan.cashcalendar.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.activities.DetailPayActivity;
import com.might.dwan.cashcalendar.ui.activities.SettingActivity;

/**
 * Created by Might on 13.09.2017.
 */

public class IntentUtils {

    public static void startDetailPay(Context context, int mode, CostItem item) {
        Intent intent = new Intent(context, DetailPayActivity.class);
        intent.putExtra(ConstantManager.EXTRA_MODE, mode);
        intent.putExtra(ConstantManager.EXTRA_ITEM, item);
        startActivity(context, intent, ConstantManager.REQUEST_ACTIVITY_DETAIL);
    }

    public static void goCapture(Context context, String output) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(output));
        startActivity(context, intent, ConstantManager.REQUEST_CAMERA);
    }

    public static void pickPhotoFromGallery(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivity(context, intent, ConstantManager.REQUEST_GALLERY_PICK);
    }

    public static void startSettings(Context context) {
        startActivity(context, SettingActivity.class);
    }


    public static void startActivity(Context context, Class cl) {
        startActivity(context, new Intent(context, cl));
    }

    private static void startActivity(Context c, Intent intent) {
        startActivity(c, intent, -1);
    }

    private static void startActivity(Context c, Intent intent, int requestCode) {
        try {
            if (requestCode == -1) {
                ((AppCompatActivity) c).startActivity(intent);
            } else {
                ((AppCompatActivity) c).startActivityForResult(intent, requestCode);
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
