package com.example.truequeworld.Interface;


import com.example.truequeworld.Class.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServiceApi {

    @GET("/product/products")
    Call<List<Product>> getProducts();
}

