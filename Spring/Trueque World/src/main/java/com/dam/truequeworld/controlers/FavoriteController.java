package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.servicies.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorite")
    public List<Favorite> getFavorite(){
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/id/{id}")
    public Favorite getFavoriteById(@PathVariable Integer id){
        return favoriteService.getFavoriteById(id);
    }

    @PostMapping("/save")
    public Favorite insertFavorite(@RequestBody Favorite favorite){
        return favoriteService.saveFavorite(favorite);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteFavoriteById(@PathVariable Integer id){
        return favoriteService.deleteFavoriteById(id);
    }
}
