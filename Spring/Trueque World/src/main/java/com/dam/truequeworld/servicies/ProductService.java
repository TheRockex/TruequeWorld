package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public boolean deleteProductById(Integer id){
        productRepository.deleteById(id);
        return productRepository.findById(id).isEmpty();
    }
}
