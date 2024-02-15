package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
