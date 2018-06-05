package com.might.dwan.cashcalendar.ui.adapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Might on 13.09.2017.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceDecoration(int space) {
        this.space = space;
    }

    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);
        outRect.bottom = space;
    }
}
