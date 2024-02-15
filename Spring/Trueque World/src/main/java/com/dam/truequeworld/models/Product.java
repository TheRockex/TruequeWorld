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

    @Column(name = "valorenTP")
    private BigDecimal valorenTP;

    @Column(name = "estado")
    private String estado;

    @Column(name = "usuarioid")
    private Integer usuarioId;

    @Column(name = "img")
    private String imgProducto;
    @Column(name = "categoria")
    private String categoria;
}

