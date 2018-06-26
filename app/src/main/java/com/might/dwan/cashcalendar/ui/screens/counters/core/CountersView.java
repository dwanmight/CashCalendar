package com.might.dwan.cashcalendar.ui.screens.counters.core;

import android.graphics.Canvas;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.adapter.decoration.SpaceDecoration;
import com.might.dwan.cashcalendar.ui.screens.counters.CountersFragment;
import com.might.dwan.cashcalendar.ui.screens.counters.list.CountersAdapter;
import com.might.dwan.cashcalendar.ui.screens.counters.list.ItemSwipeCallback;
import com.might.dwan.cashcalendar.utils.DisplayUtils;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Ilya on 05.06.2018.
 */

public class CountersView {

    private View rootView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private CountersAdapter mAdapter;

    private ItemSwipeCallback mItemSwipeCallback = new ItemSwipeCallback() {
        @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override public void onChildDraw(Canvas c, RecyclerView rv, RecyclerView.ViewHolder vh,
                                          float dX, float dY, int action, boolean isActive) {
            //            if (vh instanceof PayCounterVH) {
            //                if (dX > -300 && dX <= 0) {
            //                    ((PayCounterVH) vh).frontView.setTranslationX(dX);
            //                }
            //            }
        }
    };

    public CountersView(CountersFragment fragment) {
        View v = fragment.getView();
        rootView = v;
        mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mFab = v.findViewById(R.id.add_fab);

    }

    void bindClickListener(CountersContractor.IPresenter listener) {
        if (listener != null) {
            mFab.setOnClickListener(v -> listener.onClickAdd());
            mSwipeRefreshLayout.setOnRefreshListener(listener::onRefresh);
        } else {
            mFab.setOnClickListener(null);
            mSwipeRefreshLayout.setOnRefreshListener(null);
        }
    }

    public void setupAdapter(ArrayList<CostItem> list) {
        mAdapter = new CountersAdapter(list);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceDecoration(
                DisplayUtils.pxToDp(rootView.getContext(), 32)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mItemSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    public Observable<Integer> getItemClicks() {return mAdapter.getItemClicks();}



    void adapterDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    void disableRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
