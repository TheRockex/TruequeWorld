package com.dam.truequeworld.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "favoritos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Favorite {
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Productoid")
    private Integer Productoid;

    @Column(name = "Usuarioid")
    private Integer Usuarioid;
}

