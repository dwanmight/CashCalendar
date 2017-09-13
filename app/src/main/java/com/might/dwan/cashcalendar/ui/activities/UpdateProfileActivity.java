package com.might.dwan.cashcalendar.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.db_models.UsersInfoDB;
import com.might.dwan.cashcalendar.data.manager.PreferencesManager;
import com.might.dwan.cashcalendar.data.models.UserModel;

/**
 * Created by Might on 24.08.2017.
 */

public class UpdateProfileActivity extends BaseActivity {

    private ImageView mAvatarImg;
    private EditText mNickNameEt, mNameEt, mSurnameEt;
    private Button mSaveBtn;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        initViews();
        setupViews();
    }

    private void setupViews() {
        UsersInfoDB usersInfoDB = new UsersInfoDB(this);
        UserModel user = usersInfoDB.getUser(PreferencesManager.get(this).getPreferences().getNickname());

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
    }

    private void initViews() {
        mAvatarImg = (ImageView) findViewById(R.id.update_photo_img);
        mNickNameEt = (EditText) findViewById(R.id.update_nickname_et);
        mNameEt = (EditText) findViewById(R.id.update_name_et);
        mSurnameEt = (EditText) findViewById(R.id.update_surname_et);
        mSaveBtn = (Button) findViewById(R.id.update_save_btn);
    }

    public void onClickSave(View v) {
        if (checkFields()) {
            updateProfile();
        }
    }

    private boolean checkFields() {
        if (!isValidEt(mNickNameEt)) {
            showToast("Введите никнейм");
            return false;
        }
        if (!isValidEt(mNameEt)) {
            showToast("Введите имя");
            return false;
        }

        if (!isValidEt(mSurnameEt)) {
            showToast("Введите фамилию");
            return false;
        }
        return true;
    }

    private boolean isValidEt(EditText editText) {
        return !editText.getText().toString().trim().equals("");
    }

    private void updateProfile() {
        UsersInfoDB usersInfoDB = new UsersInfoDB(this);
        usersInfoDB.addUser(getTextFromEt(mNickNameEt), getTextFromEt(mNameEt), getTextFromEt(mSurnameEt));
        PreferencesManager.get(this).getPreferences().saveNickname(mNickNameEt.getText().toString().trim());
        finish();
    }

    private String getTextFromEt(EditText editText) {
        return editText.getText().toString().trim();
    }
}
