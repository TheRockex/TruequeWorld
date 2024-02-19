package com.example.truequeworld.Class;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Mensaje {
    private Integer id;
    private Integer idChat;
    private Integer idUsuario;
    private String texto;
    private Timestamp fecha;

    public Mensaje(Integer id, Integer idChat, Integer idUsuario, String texto, Timestamp fecha) {
        this.id = id;
        this.idChat = idChat;
        this.idUsuario = idUsuario;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
