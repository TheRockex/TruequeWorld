package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNombreContainingOrCategoriaContaining(String nombre, String categoria);
    @Query("SELECT p FROM Product p WHERE p.estado = :estado")
    List<Product> getProductsByEstado(Integer estado);
}
