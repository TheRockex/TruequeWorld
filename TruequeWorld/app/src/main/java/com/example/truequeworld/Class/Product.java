package com.example.truequeworld.Class;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;

    private String nombre;

    private String descripcion;

    private Integer valorenTP;

    private String estado;

    private Integer usuarioId;

    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorenTP() {
        return valorenTP;
    }

    public void setValorenTP(Integer valorenTP) {
        this.valorenTP = valorenTP;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return usuarioId;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.usuarioId = usuarioId;
    }

}
