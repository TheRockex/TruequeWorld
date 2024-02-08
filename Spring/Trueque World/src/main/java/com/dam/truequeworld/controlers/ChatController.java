package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ProductService productService;

    @GetMapping("/chats")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping("/save")
    public Product insertProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }
}
