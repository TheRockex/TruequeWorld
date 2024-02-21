package com.dam.truequeworld.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valor_tp")
    private Integer valorenTP;

    @Column(name = "estado")
    private String estado;

    @Column(name = "usuarioid")
    private Integer usuarioId;

    @Column(name = "img")
    private String imgProducto;
    @Column(name = "categoria")
    private String categoria;
    private byte[] imgBytes;
    public Product(String nombre, String descripcion, Integer valorenTP, String estado, Integer usuarioId, String categoria, byte[] img){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorenTP = valorenTP;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.categoria = categoria;
        this.imgBytes = img;
    }
    public Product(Integer id,String nombre, String descripcion, Integer valorenTP, String estado, Integer usuarioId, String imgProducto,String categoria){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorenTP = valorenTP;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.categoria = categoria;
        this.imgProducto = imgProducto;
    }

    public byte[] getImg() {
        return imgBytes;
    }
}

