package com.andresnav.trackmyshoes.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForecastApi {

    @GET("forecast?APPID={api_key}&q={city}")
    Call<ForecastModel> getForecast(@Path("api_key") String api_key, @Path("city") String city);
}
