package com.might.dwan.cashcalendar.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.might.dwan.cashcalendar.R;
import com.might.dwan.cashcalendar.data.db.DBManager;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.models.PayCounterModel;
import com.might.dwan.cashcalendar.ui.adapter.PayCounterAdapter;
import com.might.dwan.cashcalendar.ui.adapter.decoration.SpaceDecoration;
import com.might.dwan.cashcalendar.ui.interfaces.OnItemPickListener;
import com.might.dwan.cashcalendar.utils.ConstantManager;
import com.might.dwan.cashcalendar.utils.DisplayUtils;
import com.might.dwan.cashcalendar.utils.IntentUtils;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Might on 25.08.2017.
 */

public class CounterListFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, OnItemPickListener<PayCounterModel> {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<PayCounterModel> mList;
    private PayCounterAdapter mAdapter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_counter_list, container, false);
        initViews(v);
        setupList();
        loadData(null);
        return v;
    }

    private void initViews(View v) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) v.findViewById(R.id.add_fab);
    }

    private void setupList() {
        mList = new ArrayList<>();
        mAdapter = new PayCounterAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceDecoration(DisplayUtils.pxToDp(getActivity(), 32)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadData(String last_id) {
        try {
            mList.addAll(new PayCounterDB().load(DBManager.get(getActivity()).getReadableDatabase(), 20, last_id));
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onStart() {
        super.onStart();
        setListeners();
    }

    private void setListeners() {
        mFab.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemPickListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fab:
                startDetailItem(DetailPayFragment.MODE_NEW, null);
                break;
        }
    }

    private void startDetailItem(int mode, PayCounterModel model) {
        IntentUtils.startDetailPay(getActivity(), mode, model);
    }

    @Override public void onRefresh() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        loadData(null);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override public void onItemClicked(PayCounterModel item) {
        if (item != null) {
            IntentUtils.startDetailPay(getActivity(), DetailPayFragment.MODE_DETAIL, item);
        }
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantManager.REQUEST_ACTIVITY_DETAIL:
                getResultDetail(resultCode, data);
                break;
        }
    }

    private void getResultDetail(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mList.clear();
            loadData(null);
        }
    }
}
