package com.dam.truequeworld.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "chats")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Chat {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "idInteresado")
    private Integer idInteresado;
}

