package com.example.truequeworld.Interface;


import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoriteServiceApi {

    @GET("/favorite/favorite")
    Call<List<Favorito>> getFavoritos();

    @GET("/favorite/favorites/user/{id}")
    Call<List<Favorito>> getFavoritosUserid(@Path("id") Integer id);

    @POST("/favorite/save")
    Call<Favorito> insertFavoritos(@Body Favorito favorito);

    @DELETE("/favorite/delete/{id}")
    Call<Boolean> deleteFavoriteById(@Path("id") Integer id);

    @GET("/favorite/user/{userId}")
    Call<List<Product>> getFavoriteProductsByUserId(@Path("userId") Integer userId);

}

