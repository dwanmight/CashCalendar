package com.might.dwan.cashcalendar.ui.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import com.might.dwan.cashcalendar.utils.ConstantManager;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Might on 12.09.2017.
 */

public class DatePickerDialog extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener {
    private String mTimeStamp;

    public static DatePickerDialog newInstance(String stamp) {
        Bundle args = new Bundle();
        args.putString(ConstantManager.EXTRA_STAMP, stamp);
        DatePickerDialog dialog = new DatePickerDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        mTimeStamp = getArguments().getString(ConstantManager.EXTRA_STAMP);
        if (mTimeStamp == null) mTimeStamp = String.valueOf(System.currentTimeMillis());
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTimeInMillis(Long.parseLong(mTimeStamp));
        return new android.app.DatePickerDialog(getActivity(), this, timeCalendar.get(Calendar.YEAR)
                , timeCalendar.get(Calendar.MONTH)
                , timeCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        sendResult(calendar.getTimeInMillis());
    }

    private void sendResult(long timeInMillis) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment == null) return;

        Intent intent = new Intent();
        intent.putExtra(ConstantManager.EXTRA_STAMP, timeInMillis);
        targetFragment.onActivityResult(ConstantManager.REQUEST_DATE_DIALOG, RESULT_OK, intent);
    }
}
