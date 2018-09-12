package com.might.dwan.cashcalendar.apps.modules;


import com.might.dwan.cashcalendar.data.network.ApiNetwork;
import com.might.dwan.cashcalendar.data.network.fb.FacebookAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://test")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiNetwork provideApiNetwork(Retrofit retrofit) {
        return retrofit.create(ApiNetwork.class);
    }


    @Singleton
    @Provides
    FacebookAPI provideFacebookApi() {
        return new FacebookAPI();
    }
}
