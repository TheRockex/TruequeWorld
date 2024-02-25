package com.dam.truequeworld.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "truque")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Trueque {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "producto_interesado")
    private Integer productoInteresado;

    @Column(name = "producto_solicitado")
    private Integer productoSolicitado;

    @Column(name = "estado")
    private Integer estado;
}
