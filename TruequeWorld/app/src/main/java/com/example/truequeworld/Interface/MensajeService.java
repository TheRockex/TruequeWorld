package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MensajeService {
    @GET("/mensaje/mensajes")
    Call<List<Mensaje>> getMensajes();
    
}
