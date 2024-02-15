package com.example.truequeworld;

import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionRetroFit {
    private static Retrofit instance;

    private static Retrofit getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.129.8:8086")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static UserServiceApi getUserServiceApi(){
        return getInstance().create(UserServiceApi.class);
    }

    public static ProductServiceApi getProductServiceApi(){
        return getInstance().create(ProductServiceApi.class);
    }
}
