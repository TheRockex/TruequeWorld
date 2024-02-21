package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

        byte[] img = Base64.getMimeDecoder().decode(product.getImgProducto());
        File file = new File("imgs/"+product.getNombre()+".jpg");
        try {
            file.createNewFile();
            OutputStream os = new FileOutputStream(file,false);
            os.write(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productRepository.save(product);
    }

    public boolean deleteProductById(Integer id){
        productRepository.deleteById(id);
        return productRepository.findById(id).isEmpty();
    }
}
