package com.might.dwan.cashcalendar.ui.screens.detail_item.dagger;

import com.might.dwan.cashcalendar.data.db.DBHelper;
import com.might.dwan.cashcalendar.data.db.db_writer.CategoryDB;
import com.might.dwan.cashcalendar.data.db.db_writer.PayCounterDB;
import com.might.dwan.cashcalendar.data.db.db_writer.SubcategoryDB;
import com.might.dwan.cashcalendar.data.models.CostItem;
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostModel;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostPresenter;
import com.might.dwan.cashcalendar.ui.screens.detail_item.core.DetailCostView;
import com.might.dwan.cashcalendar.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 06.06.2018.
 */

@Module
public class DetailCostModule {

    private DetailCostFragment fragment;
    private CostItem item;

    public DetailCostModule(DetailCostFragment f, CostItem item) {
        this.fragment = f;
        this.item = item;
    }

    @DetailCostScope
    @Provides DetailCostModel provideModel(CategoryDB categoryDB, SubcategoryDB subcategoryDB,
                                           PayCounterDB payCounterDB, DBHelper dbHelper) {
        return new DetailCostModel(categoryDB, subcategoryDB, payCounterDB, dbHelper, fragment);
    }

    @DetailCostScope
    @Provides DetailCostView provideView() {
        return new DetailCostView(fragment);
    }

    @DetailCostScope
    @Provides DetailCostPresenter providePresenter(DetailCostView view, DetailCostModel model,
                                                   RxSchedulers schedulers) {
        return new DetailCostPresenter(view, model, schedulers, item);
    }
}
