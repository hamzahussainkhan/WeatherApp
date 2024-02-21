package com.example.weatherapp;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView tv_temp;
    TextView tv_sunny;
    TextView tv_day1, tv_day3, tv_day2;
    TextView tv_day1temp, tv_day2temp, tv_day3temp;
    ImageView main_image;
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    //https://api.openweathermap.org/data/2.5/forecast?q=karachi&appid=0d9ecd1b144228ced9b582c9caa5d3b6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_temp = findViewById(R.id.tv_temp);
        tv_sunny = findViewById(R.id.tv_sunny);
        tv_day1 = findViewById(R.id.tv_day1);
        tv_day2 = findViewById(R.id.tv_day2);
        tv_day3 = findViewById(R.id.tv_day3);
        tv_day1temp = findViewById(R.id.tv_day1temp);
        tv_day2temp = findViewById(R.id.tv_day2temp);
        tv_day3temp = findViewById(R.id.tv_day3temp);
        main_image = findViewById(R.id.main_image);
        fetchWeatherData();
    }


    private void fetchWeatherData() {

        Retrofit ret = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = ret.create(ApiInterface.class);
        Call<Example> call = apiInterface.GetWeatherData("karachi", "0d9ecd1b144228ced9b582c9caa5d3b6", "metric");

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Log.d("API WEATHER DATA", response.body().getList().get(0).getMain().getTemp().toString());

                tv_temp.setText(response.body().getList().get(0).getMain().getTemp().toString());
                tv_day1temp.setText(response.body().getList().get(1).getMain().getTemp().toString());
                tv_day2temp.setText(response.body().getList().get(2).getMain().getTemp().toString());
                tv_day3temp.setText(response.body().getList().get(3).getMain().getTemp().toString());

                tv_sunny.setText(getTime(String.valueOf(response.body().getList().get(0).getDt())));
                tv_day1.setText(getDay(String.valueOf(response.body().getList().get(1).getDt())));
                tv_day2.setText(getDay(String.valueOf(response.body().getList().get(2).getDt())));
                tv_day3.setText(getDay(String.valueOf(response.body().getList().get(3).getDt())));


                if ( response.body().getList().get(0).getWeather().get(0).getMain().equalsIgnoreCase("cloudy")) {
                    main_image.setImageResource(R.drawable.cloudy);
                }
                else if ( response.body().getList().get(0).getWeather().get(0).getMain().equalsIgnoreCase("smoke")){
                    main_image.setImageResource(R.drawable.smoke);
                }
                else if ( response.body().getList().get(0).getWeather().get(0).getMain().equalsIgnoreCase("raining")){
                    main_image.setImageResource(R.drawable.raining);
                }
                else if ( response.body().getList().get(0).getWeather().get(0).getMain().equalsIgnoreCase("snowman")){
                    main_image.setImageResource(R.drawable.snowman);
                }
                else if ( response.body().getList().get(0).getWeather().get(0).getMain().equalsIgnoreCase("clear")){
                    main_image.setImageResource(R.drawable.clear);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


    }

    public String getTime(String time) {

        Long t = Long.valueOf(time);
        // Given milliseconds
        long milliseconds = t * 1000L; // Assuming the input is in seconds

        // Create a Date object
        Date date = new Date(milliseconds);

        // Print the result
        System.out.println("Milliseconds: " + milliseconds);
        System.out.println("Date: " + date);

        Date currentDate = date;

        // Specify the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date
        String formattedDate = dateFormat.format(currentDate);

        // Print the formatted date
        System.out.println("Formatted Date: " + formattedDate);

        return String.valueOf(formattedDate);
    }

    private String getDay(String Day) {

        Long d = Long.valueOf(Day);
        // Given milliseconds
        long milliseconds = d * 1000L; // Assuming the input is in seconds

        // Create a Date object
        Date date = new Date(milliseconds);

        // Print the result
        System.out.println("Milliseconds: " + milliseconds);
        System.out.println("Date: " + date);

        Date currentDate = date;

        // Specify the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE-MMMM-dd");

        // Format the date
        String formattedDate = dateFormat.format(currentDate);

        // Print the formatted date
        System.out.println("Formatted day: " + formattedDate);

        return String.valueOf(formattedDate);

    }
    }
