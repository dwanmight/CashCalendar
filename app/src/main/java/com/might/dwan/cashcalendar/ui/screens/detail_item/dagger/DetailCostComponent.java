package com.might.dwan.cashcalendar.ui.screens.detail_item.dagger;

import com.might.dwan.cashcalendar.apps.AppComponent;
import com.might.dwan.cashcalendar.ui.screens.detail_item.DetailCostFragment;

import dagger.Component;

/**
 * Created by Ilya on 06.06.2018.
 */

@DetailCostScope
@Component(dependencies = {AppComponent.class},modules = {DetailCostModule.class} )
public interface DetailCostComponent {
    void inject(DetailCostFragment fragment);
}
