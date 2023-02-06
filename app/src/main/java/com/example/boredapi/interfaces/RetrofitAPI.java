package com.example.boredapi.interfaces;

import com.example.boredapi.model.Maquina;
import com.example.boredapi.model.Registro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("add-apontamento")
    Call<Registro> createPost(@Body Registro registro);

    @GET("maquinas")
    Call<List<Maquina>> getMaquinas();
}
