package com.example.truequeworld.Interface;


import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductServiceApi {

    @GET("/product/products/user/{id}")
    Call<List<Product>> getProducts(@Path("id") Integer id);

    @GET("/product/id/{id}")
    Call<Product> getproductById(@Path("id") Integer id);

    @POST("/product/save")
    Call<Product> insertProduct(@Body Product product);

    @PUT("/product/save")
    Call<Product> updateProduct(@Body Product product);

    @GET("/product/buscar/{searchTerm}/{id}")
    Call<List<Product>> buscarProductosPorNombreOCategoria(@Path("searchTerm") String searchTerm, @Path("id") Integer id);

    @GET("/product/products/propietario/{id}")
    Call<List<Product>> getFavoritesUser(@Path("id") Integer id);

    @GET("/product/estado/{estado}/{userId}")
    Call<List<Product>> getProductsUserByEstado(@Path("estado") Integer estado, @Path("userId") Integer userId);
    @GET("/product/img/{id}/{id2}")
    Call<List<Product>> getImgById(@Path("id") Integer id, @Path("id2") Integer id2);
}