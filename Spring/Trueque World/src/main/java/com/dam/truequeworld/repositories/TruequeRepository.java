package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.models.Trueque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TruequeRepository extends JpaRepository<Trueque, Integer> {
    @Query("SELECT t FROM Trueque t WHERE t.estado = :estado")
    List<Trueque> getTruequeByEstado(Integer estado);
}
