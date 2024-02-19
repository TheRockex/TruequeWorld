package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MensajeService {
    @GET("/mensaje/mensajes")
    Call<List<Mensaje>> getMensajes();
    @POST("/conectarse")
    Call<Boolean> getConexion(@Body String userId);
    @POST("/enviar")
    Call<Boolean> enviarMensaje();
}
