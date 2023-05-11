package com.example.kirill;


public interface RestApi {
    @GET("api/v1/predict.json/complete")
    Call<Model> predict(@Query("key") String key, @Query("q") String q, @Query("lang") String lang);
}
