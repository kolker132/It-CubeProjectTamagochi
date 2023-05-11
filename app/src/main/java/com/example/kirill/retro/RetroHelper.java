package com.example.kirill.retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHelper {

    private static Retrofit instance = null;
    private RetroHelper() {}

    public static Retrofit getServer() {
        if (instance == null) {
            instance =  new Retrofit.Builder()
                    .baseUrl("http://192.168.1.119:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}



