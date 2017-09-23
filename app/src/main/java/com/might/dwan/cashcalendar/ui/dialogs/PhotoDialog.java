package com.might.dwan.cashcalendar.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.FileUtils;
import com.might.dwan.cashcalendar.utils.IntentUtils;

/**
 * Created by Might on 19.09.2017.
 */

public class PhotoDialog extends DialogFragment implements Dialog.OnClickListener {
    public static final int CAMERA = 0;
    public static final int GALLERY = 1;
    public static final int CANCEL = 2;

    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] items = new String[]{getString(R.string.camera), getString(R.string.galery), getString(R.string.action_cancel)};
        builder.setItems(items, this);
        return builder.create();
    }

    @Override public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case CAMERA:
                capture();
                break;
            case GALLERY:
                pickFromGallery();
                break;
            case CANCEL:
                break;
            default:
                break;
        }
        dismiss();
    }

    private void pickFromGallery() {
        IntentUtils.pickPhotoFromGallery(getActivity());
    }

    private void capture() {
        FileUtils fileUtils = new FileUtils(getActivity().getBaseContext());
        String path = fileUtils.createCameraFile();
        if (path != null) {
            Log.i(ConstantManager.TAG, "capture: "+path);
            IntentUtils.goCapture(getActivity(), path);
        } else {
            Toast.makeText(getActivity(), "Error on create file for camera", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendResult(int which) {

    }
}
