package com.example.truequeworld.Class;

import java.time.LocalDateTime;

public class Mensaje {
    private Integer id;
    private Integer idChat;
    private Integer idUsuario;
    private String texto;
    private LocalDateTime fecha;

    public Mensaje(Integer id, Integer idChat, Integer idUsuario, String texto, LocalDateTime fecha) {
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
