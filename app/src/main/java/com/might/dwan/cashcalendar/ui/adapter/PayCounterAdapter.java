package com.might.dwan.cashcalendar.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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
    private LayoutInflater mLayoutInflater;

    private final int TYPE_PLACE_HOLDER = 0;
    private final int TYPE_ITEM = 1;

    public PayCounterAdapter(ArrayList<PayCounterModel> list) {
        mList = list;
    }

    public void setOnItemPickListener(OnItemPickListener<PayCounterModel> onItemPickListener) {
        mOnItemPickListener = onItemPickListener;
    }


    @NonNull
    @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup root, int type) {
        switch (type) {
            case TYPE_ITEM:
                return new PayCounterHolder(createView(R.layout.list_item_paycounter_item, root));
            case TYPE_PLACE_HOLDER:
            default:
                return new PlaceHolder(createView(
                        R.layout.list_item_paycounter_place_holder, root));
        }
    }

    private View createView(@LayoutRes int id, ViewGroup container) {
        checkInflaterForNull(container);
        return mLayoutInflater.inflate(id, container, false);
    }

    private void checkInflaterForNull(ViewGroup container) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(container.getContext());
        }
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
        private ViewGroup root;
        private TextView category_tv;
        private TextView subcategory_tv;
        private TextView pay_tv;
        private TextView date_tv;

        public PayCounterHolder(View v) {
            super(v);
            root = (ViewGroup) v;
            category_tv = v.findViewById(R.id.list_item_paycounter_category_tv);
            subcategory_tv = v.findViewById(R.id.list_item_paycounter_subcategory_tv);
            pay_tv = v.findViewById(R.id.list_item_paycounter_pay_tv);
            date_tv = v.findViewById(R.id.list_item_paycounter_date_tv);
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
            root.setOnClickListener(v -> {
                if (mOnItemPickListener != null) {
                    mOnItemPickListener.onItemClicked(item);
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
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public static class PlaceHolder extends RecyclerView.ViewHolder {

        public PlaceHolder(View v) {
            super(v);
        }
    }
}
