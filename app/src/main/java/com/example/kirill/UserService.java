package com.example.kirill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @POST("/user")
    @FormUrlEncoded
    public Call<List<User>> users(@Field("email")String email, @Field("pwd") String pass);
    @PUT("/user")
    Call<Void> saveUser(@Body User user);
}
