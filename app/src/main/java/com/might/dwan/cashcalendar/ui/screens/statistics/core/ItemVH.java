package com.might.dwan.cashcalendar.ui.screens.statistics.core;

import android.view.View;
import android.widget.TextView;

import com.might.dwan.cashcalendar.R;

/**
 * Created by Ilya on 19.06.2018.
 */

public class ItemVH {
    private View root;

    public ItemVH(View root) {
        this.root = root;
    }

    public TextView initDate() {
        return root.findViewById(R.id.list_item_paycounter_date_tv);
    }

    public TextView initAmount() {
        return root.findViewById(R.id.list_item_paycounter_pay_tv);
    }

    public TextView initCategory() {
        return root.findViewById(R.id.list_item_paycounter_category_tv);
    }

    public TextView initSubcategory() {
        return root.findViewById(R.id.list_item_paycounter_subcategory_tv);
    }
}
