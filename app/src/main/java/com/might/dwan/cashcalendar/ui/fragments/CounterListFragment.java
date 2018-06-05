package com.might.dwan.cashcalendar.ui.fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class CounterListFragment extends BaseFragment
        implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        OnItemPickListener<PayCounterModel> {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<PayCounterModel> mList;
    private PayCounterAdapter mAdapter;

    @Override public int getLayoutId() {return R.layout.fragment_counter_list;}

    @Override public void initUI(View v) {
        mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mFab = v.findViewById(R.id.add_fab);
    }

    @Override public void setupData() {
        mList = new ArrayList<>();
        mAdapter = new PayCounterAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceDecoration(DisplayUtils.pxToDp(getActivity(), 32)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData(null);
    }

    private void loadData(String last_id) {
        try {
            mList.addAll(new PayCounterDB().load(DBManager.get(getActivity()).getReadableDatabase(), 20, last_id));
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void setListeners(boolean enable) {
        if (enable) {
            mFab.setOnClickListener(this);
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mAdapter.setOnItemPickListener(this);
        } else {
            mFab.setOnClickListener(null);
            mSwipeRefreshLayout.setOnRefreshListener(null);
            mAdapter.setOnItemPickListener(null);
        }
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
