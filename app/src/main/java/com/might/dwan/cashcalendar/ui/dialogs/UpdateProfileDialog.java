package com.might.dwan.cashcalendar.ui.dialogs;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;

import com.might.dwan.cashcalendar.ui.interfaces.OnOkListener;

public class UpdateProfileDialog extends DialogFragment {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SURNAME = "surname";

    private OnOkListener mOnOkListener;

    public static UpdateProfileDialog newInstance(String name, String surname) {
        Bundle args = new Bundle();
        args.putString(EXTRA_NAME, name);
        args.putString(EXTRA_SURNAME, surname);

        UpdateProfileDialog dialog = new UpdateProfileDialog();
        dialog.setArguments(args);
        return dialog;
    }

    public void setOkListener(OnOkListener l) {
        this.mOnOkListener = l;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getTitle());
        dialog.setNegativeButton(android.R.string.cancel, (d, w) -> dismiss());
        dialog.setPositiveButton(android.R.string.ok, (d, w) -> {
            if (mOnOkListener != null) mOnOkListener.onClickOk();
            dismiss();
        });

        return dialog.create();
    }

    private SpannableStringBuilder getTitle() {
        Bundle arguments = getArguments();
        String name = arguments.getString(EXTRA_NAME);
        String surname = arguments.getString(EXTRA_SURNAME);

        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append("Update your info to ");
        titleBuilder.append(name);
        titleBuilder.append(" ");
        titleBuilder.append(surname);

        SpannableStringBuilder spanBuilder = new SpannableStringBuilder(titleBuilder);
        spanBuilder.setSpan(new StyleSpan(Typeface.BOLD),
                titleBuilder.length() - name.length() - surname.length(),
                titleBuilder.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanBuilder;
    }

}
