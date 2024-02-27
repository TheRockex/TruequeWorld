package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Trueque;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TruequeServiceApi {
    @POST("/trueque/save")
    Call<Trueque> insertTrueque(@Body Trueque trueque);

    @GET("/trueque/solicitud/{userId}/{estado}")
    Call<List<Trueque>> getTruequesByUserEstado(@Path("userId") Integer userId, @Path("estado") Integer estado);
}
