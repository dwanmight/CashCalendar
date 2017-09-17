package com.might.dwan.cashcalendar.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.activities.DetailPayActivity;

/**
 * Created by Might on 13.09.2017.
 */

public class IntentUtils {

    public static void startDetailPay(Context context, int mode, PayCounterModel item) {
        Intent i = new Intent(context, DetailPayActivity.class);
        i.putExtra(ConstantManager.EXTRA_MODE, mode);
        i.putExtra(ConstantManager.EXTRA_ITEM, item);
        ((AppCompatActivity) context).startActivityForResult(i, ConstantManager.REQUEST_ACTIVITY_DETAIL);
    }
}
