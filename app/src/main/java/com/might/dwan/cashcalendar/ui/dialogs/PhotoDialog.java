package com.might.dwan.cashcalendar.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.utils.ConstantManager;

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
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        getActivity().startActivityForResult(intent, ConstantManager.REQUEST_GALLERY_PICK);
    }

    private void sendResult(int which) {

    }
}
