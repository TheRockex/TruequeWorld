package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Trueque;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TruequeServiceApi {
    @POST("/trueque/save")
    Call<Trueque> insertTrueque(@Body Trueque trueque);
}
