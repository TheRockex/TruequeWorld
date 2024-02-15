package com.example.truequeworld.Interface;


import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductServiceApi {

    @GET("/product/products")
    Call<List<Product>> getProducts();

    @GET("/product/id/{id}")
    Call<Product> getproductById(@Path("id") Integer id);

    @POST("/product/save")
    Call<Product> insertProduct(@Body Product product);
}

