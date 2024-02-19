package com.dam.truequeworld.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Mensaje {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idChat")
    private Integer idChat;

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "texto")
    private String texto;

    @Column(name = "fecha")
    private String fecha;
}
