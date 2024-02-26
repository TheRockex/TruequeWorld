package com.example.truequeworld.Class;

public class Trueque {
    private Integer id;
    private Integer productoInteresado;
    private Integer productoSolicitado;
    private Integer estado;

    public Trueque(Integer id, Integer productoInteresado, Integer productoSolicitado, Integer estado) {
        this.id = id;
        this.productoInteresado = productoInteresado;
        this.productoSolicitado = productoSolicitado;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoInteresado() {
        return productoInteresado;
    }

    public void setProductoInteresado(Integer productoInteresado) {
        this.productoInteresado = productoInteresado;
    }

    public Integer getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Integer productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
