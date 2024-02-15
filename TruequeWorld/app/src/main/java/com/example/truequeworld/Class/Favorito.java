package com.example.truequeworld.Class;

public class Favorito {
    private Integer id;

    private Integer usuarioid;

    private Integer productoid;

    public Favorito(Integer id, Integer usuarioid, Integer productoid) {
        this.id = id;
        this.usuarioid = usuarioid;
        this.productoid = productoid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioid;
    }

    public void setUsuarioId(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Integer getProductoId() {
        return productoid;
    }

    public void setProductoId(Integer productoid) {
        this.productoid = productoid;
    }


}
