package com.might.dwan.cashcalendar.ui.screens.counters.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.utils.DateUtils;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Ilya on 06.06.2018.
 */

class PayCounterVH extends RecyclerView.ViewHolder {
    private ViewGroup rootView;
    private TextView categoryTv;
    private TextView subcategoryTv;
    private TextView payTv;
    private TextView dateTv;


    public PayCounterVH(View v, PublishSubject<Integer> subjects) {
        super(v);
        rootView = (ViewGroup) v;
        categoryTv = v.findViewById(R.id.list_item_paycounter_category_tv);
        subcategoryTv = v.findViewById(R.id.list_item_paycounter_subcategory_tv);
        payTv = v.findViewById(R.id.list_item_paycounter_pay_tv);
        dateTv = v.findViewById(R.id.list_item_paycounter_date_tv);
        rootView.setOnClickListener(view -> subjects.onNext(getAdapterPosition()));
    }

    public void bind(PayCounterModel item) {
        clearData();
        setCategory(item.getCategory_text());
        setSubcategory(item.getSubcategory_text());
        setPay(item.getCount_pay());
        setDate(item.getTimestamp());
    }

    private void clearData() {
        categoryTv.setText("");
        subcategoryTv.setText("");
        payTv.setText("");
        dateTv.setText("");
    }

    private void setCategory(String category) {
        categoryTv.setText(category);
    }

    private void setSubcategory(String subcategory) {
        subcategoryTv.setText(subcategory);
    }

    private void setPay(String pay) {
        payTv.setText(pay);
    }

    private void setDate(String date) {
        try {
            dateTv.setText(DateUtils.stampToYMDHMS(Long.parseLong(date)));
        } catch (Exception e) {e.printStackTrace();}
    }
}