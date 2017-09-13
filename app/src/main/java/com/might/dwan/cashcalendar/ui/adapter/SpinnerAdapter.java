package com.might.dwan.cashcalendar.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.ui.adapter.adapter_models.NameIdModel;

import java.util.ArrayList;

/**
 * Created by Might on 02.09.2017.
 */

public class SpinnerAdapter extends ArrayAdapter<NameIdModel> {
    private ArrayList<NameIdModel> mData;
    private Context mContext;

    public SpinnerAdapter(Context context, ArrayList<NameIdModel> data) {
        super(context, 0, data);
        mContext = context;
        mData = data;
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_spinner_name_id, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.clear();
        }
        viewHolder.setup(mData.get(position));
        return convertView;
    }

    public static class ViewHolder {
        private TextView mNameTv;

        public ViewHolder(View v) {
            mNameTv = (TextView) v.findViewById(R.id.list_item_spinner_tv);
        }

        private void clear() {
            mNameTv.setText("");
        }

        private void setup(NameIdModel item) {
            if (item == null) return;
            mNameTv.setText(item.getName());
        }
    }

    public int getId(int pos) {
        return mData.get(pos).getId();
    }


    @Override public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_spinner_name_id, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.clear();
        }
        viewHolder.setup(mData.get(position));
        return convertView;
    }
}
