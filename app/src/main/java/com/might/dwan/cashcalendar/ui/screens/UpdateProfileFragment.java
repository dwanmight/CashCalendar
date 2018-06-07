package com.might.dwan.cashcalendar.ui.screens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.DBManager;
import com.might.dwan.cashcalendar.data.db.db_writer.UsersInfoDB;
import com.might.dwan.cashcalendar.data.manager.PreferencesManager;
import com.might.dwan.cashcalendar.data.models.UserModel;
import com.might.dwan.cashcalendar.ui.dialogs.PhotoDialog;
import com.might.dwan.cashcalendar.ui.screens.BaseFragment;
import com.might.dwan.cashcalendar.utils.BitmapUtils;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.EditTextUtils;
import com.might.dwan.cashcalendar.utils.ValidUtils;

/**
 * Created by Might on 19.09.2017.
 */

public class UpdateProfileFragment extends BaseFragment
        implements View.OnClickListener {

    private ImageView mAvatarImg;
    private EditText mNickNameEt, mNameEt, mSurnameEt;
    private Button mSaveBtn;

    @Override public int getLayoutId() {return R.layout.fragment_update_profile;}

    @Override public void initUI(View v) {
        mAvatarImg = v.findViewById(R.id.update_photo_img);
        mNickNameEt = v.findViewById(R.id.update_nickname_et);
        mNameEt = v.findViewById(R.id.update_name_et);
        mSurnameEt = v.findViewById(R.id.update_surname_et);
        mSaveBtn = v.findViewById(R.id.update_save_btn);

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_profile);
        setToolbar(toolbar);
    }

    @Override public void setupData(Bundle state) {
        try {
            String nickName = PreferencesManager.get(getActivity()).getPreferences().getNickname();
            UsersInfoDB usersInfoDB = new UsersInfoDB();
            UserModel user = usersInfoDB.getUser(DBManager.get(getActivity()).getWritableDatabase(),
                    nickName);

            setTextIfNeed(mNickNameEt, user.getNickname());
            setTextIfNeed(mNameEt, user.getName());
            setTextIfNeed(mSurnameEt, user.getSurname());
        } catch (Exception e) {e.printStackTrace();}
    }

    private void setTextIfNeed(TextView v, String text) {
        if (ValidUtils.isTextValid(text)) {
            v.setText(text);
        }
    }

    @Override public void setListeners(boolean enable) {
        if (enable) {
            mSaveBtn.setOnClickListener(this);
            mAvatarImg.setOnClickListener(this);
        } else {
            mSaveBtn.setOnClickListener(null);
            mAvatarImg.setOnClickListener(null);
        }
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_save_btn:
                clickSave();
                break;
            case R.id.update_photo_img:
                openPhotoDialog();
                break;
        }
    }

    public void clickSave() {
        if (checkFields()) {
            updateProfile();
        }
    }

    private boolean checkFields() {
        if (!EditTextUtils.isValid(mNickNameEt)) {
            showToast(getString(R.string.profile_enter_nickname));
            return false;
        }
        if (!EditTextUtils.isValid(mNameEt)) {
            showToast(getString(R.string.profile_enter_name));
            return false;
        }

        if (!EditTextUtils.isValid(mSurnameEt)) {
            showToast(getString(R.string.profile_enter_surname));
            return false;
        }
        return true;
    }

    private void updateProfile() {
        try {
            UsersInfoDB usersInfoDB = new UsersInfoDB();
            usersInfoDB.addUser(DBManager.get(getActivity()).getReadableDatabase(),
                    EditTextUtils.getText(mNickNameEt),
                    EditTextUtils.getText(mNameEt),
                    EditTextUtils.getText(mSurnameEt));
            PreferencesManager.get(getActivity()).getPreferences().saveNickname(mNickNameEt.getText().toString().trim());
            getActivity().finish();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void openPhotoDialog() {
        PhotoDialog dialog = new PhotoDialog();
        dialog.show(getFragmentManager(), dialog.getClass().getSimpleName());
    }



    //Activity result region
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean result = resultCode == Activity.RESULT_OK;
        switch (requestCode) {
            case ConstantManager.REQUEST_GALLERY_PICK:
                getResultPickGallery(result, data);
                break;
            case ConstantManager.REQUEST_CAMERA:
                getResultCamera(result, data);
                break;
        }
    }

    private void getResultPickGallery(boolean result, Intent data) {
        if (data == null) return;
        if (!result) return;
        mAvatarImg.setImageURI(data.getData());
        setPhoto(data.getDataString());
    }

    private void getResultCamera(boolean result, Intent data) {
        if (data == null) return;
        if (!result) return;
        setPhoto(PreferencesManager.get(getActivity()).getPreferences().getPhotoPath());
    }

    private void setPhoto(String photoPath) {
        if (!ValidUtils.isTextValid(photoPath)) return;
        try {
            Bitmap first = BitmapUtils.getBitmapFromUri(getActivity(), Uri.parse(photoPath));
            mAvatarImg.setImageBitmap(first);
        } catch (Exception e) {e.printStackTrace();}
    }
}
