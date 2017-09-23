package com.might.dwan.cashcalendar.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.DBManager;
import com.might.dwan.cashcalendar.data.db.db_models.UsersInfoDB;
import com.might.dwan.cashcalendar.data.manager.PreferencesManager;
import com.might.dwan.cashcalendar.data.models.UserModel;
import com.might.dwan.cashcalendar.ui.dialogs.PhotoDialog;
import com.might.dwan.cashcalendar.utils.ConstantManager;

/**
 * Created by Might on 19.09.2017.
 */

public class UpdateProfileFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mAvatarImg;
    private EditText mNickNameEt, mNameEt, mSurnameEt;
    private Button mSaveBtn;

    private PhotoDialog mPhotoDialog;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_profile, container, false);
        initViews(v);
        setupViews();
        return v;
    }

    private void initViews(View v) {
        mAvatarImg = (ImageView) v.findViewById(R.id.update_photo_img);
        mNickNameEt = (EditText) v.findViewById(R.id.update_nickname_et);
        mNameEt = (EditText) v.findViewById(R.id.update_name_et);
        mSurnameEt = (EditText) v.findViewById(R.id.update_surname_et);
        mSaveBtn = (Button) v.findViewById(R.id.update_save_btn);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_profile);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void setupViews() {
        try {
            UsersInfoDB usersInfoDB = new UsersInfoDB();
            UserModel user = usersInfoDB.getUser(DBManager.get(getActivity()).getWritableDatabase(), PreferencesManager.get(getActivity()).getPreferences().getNickname());

            String user_nickname = user.getNickname();
            String user_name = user.getName();
            String user_surname = user.getSurname();
            if (user_nickname != null && !user_nickname.trim().equals("")) {
                mNickNameEt.setText(user_nickname);
            }

            if (user_name != null && !user_name.trim().equals("")) {
                mNameEt.setText(user_name);
            }

            if (user_surname != null && !user_surname.trim().equals("")) {
                mSurnameEt.setText(user_surname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onStart() {
        super.onStart();
        setListeners();
    }

    private void setListeners() {
        mSaveBtn.setOnClickListener(this);
        mAvatarImg.setOnClickListener(this);
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
        if (!isValidEt(mNickNameEt)) {
            showToast(getString(R.string.profile_enter_nickname));
            return false;
        }
        if (!isValidEt(mNameEt)) {
            showToast(getString(R.string.profile_enter_name));
            return false;
        }

        if (!isValidEt(mSurnameEt)) {
            showToast(getString(R.string.profile_enter_surname));
            return false;
        }
        return true;
    }

    private boolean isValidEt(EditText editText) {
        return !editText.getText().toString().trim().equals("");
    }

    private void updateProfile() {
        try {
            UsersInfoDB usersInfoDB = new UsersInfoDB();
            usersInfoDB.addUser(DBManager.get(getActivity()).getReadableDatabase(), getTextFromEt(mNickNameEt), getTextFromEt(mNameEt), getTextFromEt(mSurnameEt));
            PreferencesManager.get(getActivity()).getPreferences().saveNickname(mNickNameEt.getText().toString().trim());
            getActivity().finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextFromEt(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void openPhotoDialog() {
        mPhotoDialog = new PhotoDialog();
        mPhotoDialog.show(getFragmentManager(), "PhotoDialog");
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantManager.REQUEST_GALLERY_PICK:
                getResultPickGallery(resultCode, data);
                break;
            case ConstantManager.REQUEST_CAMERA:
                getResultCamera(resultCode, data);
                break;
        }
    }

    private void getResultPickGallery(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) return;
            mAvatarImg.setImageURI(data.getData());
        }
    }

    private void getResultCamera(int resultCode, Intent data) {
        if(resultCode==Activity.RESULT_OK){
            if(data!=null){

            }
        }
    }
}
