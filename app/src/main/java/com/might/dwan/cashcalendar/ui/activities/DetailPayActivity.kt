package com.might.dwan.cashcalendar.ui.activities

import android.support.v4.app.Fragment
import com.might.dwan.cashcalendar.data.models.CostItem
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment
import com.might.dwan.cashcalendar.utils.ConstantManager

/**
 * Created by Might on 27.08.2017.
 */

class DetailPayActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        val mode = intent.getIntExtra(ConstantManager.EXTRA_MODE, 0)
        val model = intent.getSerializableExtra(ConstantManager.EXTRA_ITEM) as? CostItem
        return DetailCostFragment.newInstance(mode, model)
    }
}