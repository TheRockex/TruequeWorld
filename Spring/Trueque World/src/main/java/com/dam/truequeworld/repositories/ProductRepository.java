package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNombreContainingOrCategoriaContaining(String nombre, String categoria);
    @Query("SELECT p FROM Product p WHERE p.estado = :estado")
    List<Product> getProductsByEstado(Integer estado);

    @Query("SELECT p FROM Product p WHERE p.usuarioId = :userId")
    List<Product> findByUserId(Integer userId);

    @Query("SELECT p FROM Product p WHERE p.estado = :estado and p.usuarioId = :usuarioId")
    List<Product> getProductsUserByEstado(Integer estado, Integer usuarioId);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.estado = :estado WHERE p.id = :productId")
    Integer updateEstado(Integer estado, Integer productId);
}
