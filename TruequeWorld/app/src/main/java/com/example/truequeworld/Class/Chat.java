package com.example.truequeworld.Class;

public class Chat {
    private Integer id;
    private Integer idProducto;
    private Integer idInteresado;

    public Chat(Integer id, Integer idProducto, Integer idInteresado) {
        this.id = id;
        this.idProducto = idProducto;
        this.idInteresado = idInteresado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdInteresado() {
        return idInteresado;
    }

    public void setIdInteresado(Integer idInteresado) {
        this.idInteresado = idInteresado;
    }
}
