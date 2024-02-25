package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public boolean deleteProductById(Integer id){
        productRepository.deleteById(id);
        return productRepository.findById(id).isEmpty();
    }

    public List<Product> buscarPorNombreOCategoria(String searchTerm) {
        if (searchTerm.equals("all")) {
            return productRepository.findAll();
        } else {
            return productRepository.findByNombreContainingOrCategoriaContaining(searchTerm, searchTerm);
        }
    }

    public List<Product> getProductsByEstado(Integer estado){
        return productRepository.getProductsByEstado(estado);
    }
}
