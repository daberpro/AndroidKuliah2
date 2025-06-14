package com.daberdev.learn.services;

import com.daberdev.learn.model.ForexModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForexInterface {
    @GET("latest.json")
    Call<ForexModel> GetForexData(
            @Query("app_id") String appId
    );

    @GET("currencies.json")
    Call<Map<String,String>> GetCurrencyDescription();
}
