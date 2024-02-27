package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.models.Trueque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TruequeRepository extends JpaRepository<Trueque, Integer> {
    @Query("SELECT t FROM Trueque t WHERE t.estado = :estado")
    List<Trueque> getTruequeByEstado(Integer estado);

    @Query("SELECT t FROM Trueque t JOIN Product p on p.id = t.productoInteresado " +
            "JOIN User u on u.id = p.usuarioId WHERE p.usuarioId = :userId and t.estado = :estado")
    List<Trueque> getTruqueByUserEstado(Integer userId, Integer estado);
}
