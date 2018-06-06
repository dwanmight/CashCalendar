package com.might.dwan.cashcalendar.ui.screens.counters.list;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Might on 25.08.2017.
 */

public class PayCounterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<PayCounterModel> mList;

    private LayoutInflater mLayoutInflater;

    private final PublishSubject<Integer> mItemClicks = PublishSubject.create();

    private final int TYPE_PLACE_HOLDER = 0;
    private final int TYPE_ITEM = 1;

    public PayCounterAdapter(ArrayList<PayCounterModel> list) {
        mList = list;
    }

    @NonNull
    @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup root, int type) {
        RecyclerView.ViewHolder h;
        switch (type) {
            case TYPE_ITEM:
                h = new PayCounterVH(createView(R.layout.list_item_paycounter_item, root),
                        mItemClicks);
                break;
            case TYPE_PLACE_HOLDER:
            default:
                h = new PlaceHolderVH(createView(R.layout.list_item_paycounter_place_holder, root));
                break;
        }
        return h;
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
        if (holder instanceof PayCounterVH) {
            ((PayCounterVH) holder).bind(mList.get(position));
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

    public Observable<Integer> getItemClicks() {
        return mItemClicks;
    }

}
