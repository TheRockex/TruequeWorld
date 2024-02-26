package com.dam.truequeworld.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email")
    private String email;

    @Column(name = "contrasena")
    private String contrasenia;

    @Column(name = "img")
    private String imgPerfil;

    @Column(name = "truquepoints")
    private Integer truquepoints;

    @Column(name = "preferencias")
    private String preferencias;

    @Column(name = "movil")
    private Integer movil;
}
