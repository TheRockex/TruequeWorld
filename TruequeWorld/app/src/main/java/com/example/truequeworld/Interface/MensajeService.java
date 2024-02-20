package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MensajeService {
    @GET("/mensaje/mensajes")
    Call<List<Mensaje>> getMensajes();
    @POST("/mensaje/conectarse/{userId}")
    Call<Boolean> getConexion(@Path("userId") String userId);
    @POST("/mensaje/enviar/{mensaje}/{userId}")
    Call<Boolean> enviarMensaje(@Path("mensaje") String mensaje,@Path("userId") String userId);
}
