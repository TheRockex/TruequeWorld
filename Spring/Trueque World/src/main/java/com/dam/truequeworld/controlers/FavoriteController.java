package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.servicies.FavoriteService;
import com.dam.truequeworld.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ProductController  productService;

    @Autowired
    private ProductController  productController;

    @GetMapping("/favorite")
    public List<Favorite> getFavorite(){
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/favorites/user/{id}")
    public List<Favorite> getFavoritesUser(@PathVariable Integer id){
        return favoriteService.getFavoritesByUserId(id);
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

    @GetMapping("/user/{userId}")
    public List<Product> getFavoriteProductsByUserId(@PathVariable Integer userId) {
        List<Integer> productIds = new ArrayList<>();
        for (Favorite f : favoriteService.getFavoritesByUserId(userId)) {
            if(f.getProductoid() != null){
                productIds.add(f.getProductoid());
            }
        }

        List<Product> favoriteProducts = new ArrayList<>();
        for (Integer productId : productIds) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                favoriteProducts.add(product);
            }
        }
        for (int i = 0; i < favoriteProducts.size(); i++) {
            productController.setImg(favoriteProducts.get(i));
        }
        return favoriteProducts;
    }
}
