package com.might.dwan.cashcalendar.ui.screens.counters.list;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Ilya on 19.06.2018.
 */

public class ItemSwipeCallback extends ItemTouchHelper.SimpleCallback {
    public ItemSwipeCallback() {
        super(0, ItemTouchHelper.LEFT);
    }

    @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder
            viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
