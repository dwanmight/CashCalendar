package com.might.dwan.cashcalendar.ui.screens.update_profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.might.dwan.cashcalendar.R
import com.might.dwan.cashcalendar.apps.App
import com.might.dwan.cashcalendar.data.db.DBManager
import com.might.dwan.cashcalendar.data.db.db_writer.UsersInfoDB
import com.might.dwan.cashcalendar.data.preferences.Preferences
import com.might.dwan.cashcalendar.ui.dialogs.PhotoDialog
import com.might.dwan.cashcalendar.ui.screens.BaseFragment
import com.might.dwan.cashcalendar.ui.screens.update_profile.dagger.DaggerUpdateProfileComponent
import com.might.dwan.cashcalendar.ui.screens.update_profile.dagger.UpdateProfileComponent
import com.might.dwan.cashcalendar.utils.BitmapUtils
import com.might.dwan.cashcalendar.utils.ConstantManager
import com.might.dwan.cashcalendar.utils.EditTextUtils
import com.might.dwan.cashcalendar.utils.ValidUtils
import kotlinx.android.synthetic.main.fragment_update_profile.*
import javax.inject.Inject

/**
 * Created by Might on 19.09.2017.
 */

class UpdateProfileFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var preferences: Preferences

    private lateinit var component: UpdateProfileComponent

    override fun getLayoutId() = R.layout.fragment_update_profile

    override fun initUI(v: View) {
        toolbar.setTitle(R.string.title_profile)
        setToolbar(toolbar)
    }

    override fun setupData(state: Bundle?) {
        try {
            component = DaggerUpdateProfileComponent.builder()
                    .appComponent(App.getAppComponent())
                    .build()

            component.inject(this)

            val nickName = preferences.nickname
            val usersInfoDB = UsersInfoDB()
            val user = usersInfoDB.getUser(DBManager.get(activity).writableDatabase, nickName)

            setTextIfNeed(update_nickname_et, user.nickname)
            setTextIfNeed(update_name_et, user.name)
            setTextIfNeed(update_surname_et, user.surname)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun setTextIfNeed(v: TextView, text: String) {
        if (ValidUtils.isTextValid(text)) {
            v.text = text
        }
    }

    override fun setListeners(enable: Boolean) {
        if (enable) {
            update_save_btn.setOnClickListener(this)
            update_photo_img.setOnClickListener(this)
        } else {
            update_save_btn.setOnClickListener(null)
            update_photo_img.setOnClickListener(null)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.update_save_btn -> clickSave()
            R.id.update_photo_img -> openPhotoDialog()
        }
    }

    private fun clickSave() {
        if (checkFields()) {
            updateProfile()
        }
    }

    private fun checkFields(): Boolean {
        if (!EditTextUtils.isValid(update_nickname_et)) {
            showToast(getString(R.string.profile_enter_nickname))
            return false
        }
        if (!EditTextUtils.isValid(update_name_et)) {
            showToast(getString(R.string.profile_enter_name))
            return false
        }

        if (!EditTextUtils.isValid(update_surname_et)) {
            showToast(getString(R.string.profile_enter_surname))
            return false
        }
        return true
    }

    private fun updateProfile() {
        try {
            val usersInfoDB = UsersInfoDB()
            usersInfoDB.addUser(DBManager.get(activity).readableDatabase,
                    EditTextUtils.getText(update_nickname_et),
                    EditTextUtils.getText(update_name_et),
                    EditTextUtils.getText(update_surname_et))
            preferences.saveNickname(EditTextUtils.getText(update_nickname_et))
            activity?.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun openPhotoDialog() {
        val dialog = PhotoDialog()
        component.inject(dialog)
        dialog.show(fragmentManager, dialog.javaClass.simpleName)
    }


    //Activity result region
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = resultCode == Activity.RESULT_OK
        when (requestCode) {
            ConstantManager.REQUEST_GALLERY_PICK -> getResultPickGallery(result, data)
            ConstantManager.REQUEST_CAMERA -> getResultCamera(result, data)
            else -> return
        }
    }

    private fun getResultPickGallery(result: Boolean, data: Intent?) {
        if (data == null || !result) return
        update_photo_img.setImageURI(data.data)
        setPhoto(data.dataString)
    }

    private fun getResultCamera(result: Boolean, data: Intent?) {
        if (data == null || !result) return
        setPhoto(preferences.photoPath)
    }

    private fun setPhoto(photoPath: String?) {
        if (!ValidUtils.isTextValid(photoPath)) return
        try {
            val first = BitmapUtils.getBitmapFromUri(activity, Uri.parse(photoPath))
            update_photo_img.setImageBitmap(first)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
