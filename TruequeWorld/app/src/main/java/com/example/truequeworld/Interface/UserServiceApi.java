package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;

public interface UserServiceApi {

    @GET("/user/login/{usuario}/{contrasenia}")
    Call<Integer> getUserId(@Path("usuario") String usuario, @Path("contrasenia") String contrasenia);

    @GET("/user/id/{id}")
    Call<User> getUserById(@Path("id") Integer id);

    @POST("/user/save")
    Call<User> insertUser(@Body User user);

    @PUT("/user/save")
    Call<User> updateUser(@Body User user);

    @DELETE("/user/delete/{id}")
    Call<Boolean> deleteUserById(@Path("id") Integer id);
}
