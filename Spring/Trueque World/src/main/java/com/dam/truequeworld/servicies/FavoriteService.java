package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.repositories.FavoriteRepository;
import com.dam.truequeworld.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> getAllFavorites(){
        return favoriteRepository.findAll();
    }

    public Favorite getFavoriteById(Integer id){
        return favoriteRepository.findById(id).orElse(null);
    }

    public Favorite saveFavorite(Favorite favorite){
        return favoriteRepository.save(favorite);
    }

    public boolean deleteFavoriteById(Integer id){
        favoriteRepository.deleteById(id);
        return favoriteRepository.findById(id).isEmpty();
    }

    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
