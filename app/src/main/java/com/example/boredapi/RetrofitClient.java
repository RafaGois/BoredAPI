package com.example.boredapi;

import com.example.boredapi.interfaces.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instancia = null;
    private RetrofitAPI api;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://apiapontamendes-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RetrofitAPI.class);
    }

    public static synchronized  RetrofitClient getInstancia() {
        if (instancia == null) {
            instancia = new RetrofitClient();
        }
        return instancia;
    }

    public RetrofitAPI getApi() {
        return api;
    }
}
