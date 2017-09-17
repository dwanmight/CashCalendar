package com.might.dwan.cashcalendar.ui.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.interfaces.OnItemPickListener;
import com.might.dwan.cashcalendar.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by Might on 25.08.2017.
 */

public class PayCounterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<PayCounterModel> mList;

    private OnItemPickListener<PayCounterModel> mOnItemPickListener;

    private final int TYPE_PLACE_HOLDER = 0;
    private final int TYPE_ITEM = 1;

    public PayCounterAdapter(ArrayList<PayCounterModel> list) {
        mList = list;
    }

    public void setOnItemPickListener(OnItemPickListener<PayCounterModel> onItemPickListener) {
        mOnItemPickListener = onItemPickListener;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PLACE_HOLDER:
                return new PlaceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_paycounter_place_holder, parent, false));
            case TYPE_ITEM:
                return new PayCounterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_paycounter_item, parent, false));
            default:
                return null;
        }
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PayCounterHolder) {
            ((PayCounterHolder) holder).bind(mList.get(position));
        }
    }

    @Override public int getItemCount() {
        if (mList.size() == 0) {
            return 1;
        } else {
            return mList.size();
        }
    }

    @Override public int getItemViewType(int position) {
        if (mList.size() == 0) {
            return TYPE_PLACE_HOLDER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class PayCounterHolder extends RecyclerView.ViewHolder {
        private CardView card_view;
        private TextView category_tv;
        private TextView subcategory_tv;
        private TextView pay_tv;
        private TextView date_tv;

        public PayCounterHolder(View v) {
            super(v);
            card_view = (CardView) v;
            category_tv = (TextView) v.findViewById(R.id.list_item_paycounter_category_tv);
            subcategory_tv = (TextView) v.findViewById(R.id.list_item_paycounter_subcategory_tv);
            pay_tv = (TextView) v.findViewById(R.id.list_item_paycounter_pay_tv);
            date_tv = (TextView) v.findViewById(R.id.list_item_paycounter_date_tv);
        }

        private void bind(PayCounterModel item) {
            clearData();
            setCategory("" + item.getCategory_text());
            setSubcategory("" + item.getSubcategory_text());
            setPay(item.getCount_pay());
            setDate(item.getTimestamp());
            setListeners(item);
        }

        private void setListeners(final PayCounterModel item) {
            card_view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (mOnItemPickListener != null) {
                        mOnItemPickListener.onItemClicked(item);
                    }
                }
            });
        }

        private void clearData() {
            category_tv.setText("");
            subcategory_tv.setText("");
            pay_tv.setText("");
            date_tv.setText("");
        }

        private void setCategory(String category) {
            category_tv.setText(category);
        }

        private void setSubcategory(String subcategory) {
            subcategory_tv.setText(subcategory);
        }

        private void setPay(String pay) {
            pay_tv.setText(pay);
        }

        private void setDate(String date) {
            try {
                date_tv.setText(DateUtils.stampToYMDHMS(Long.parseLong(date)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PlaceHolder extends RecyclerView.ViewHolder {

        public PlaceHolder(View v) {
            super(v);
        }
    }
}
