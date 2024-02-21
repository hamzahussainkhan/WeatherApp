package com.example.weatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("forecast")
    public Call<Example> GetWeatherData(@Query("q") String city, @Query("appID") String appId,
                                        @Query("units")String units);

}
