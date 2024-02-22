package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query("SELECT f FROM Favorite f WHERE f.Usuarioid = :userId")
    List<Favorite> findByUserId(Integer userId);
}
