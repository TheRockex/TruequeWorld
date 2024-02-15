package com.example.truequeworld.Interface;


import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FavoriteServiceApi {

    @GET("/favorite/favorite")
    Call<List<Favorito>> getFavoritos();

    @POST("/favorite/save")
    Call<Favorito> insertFavoritos(@Body Favorito favorito);
}

