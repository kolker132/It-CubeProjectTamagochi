package com.example.kirill;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class MyCallback<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}
