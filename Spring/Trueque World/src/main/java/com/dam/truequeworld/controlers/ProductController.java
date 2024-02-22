package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Product;
//import com.dam.truequeworld.models.ProductImg;
import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.ProductService;
import com.dam.truequeworld.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/products/user/{id}")
    public List<Product> getProducts(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        List<Product> productos = productService.getAllProducts();
        List<Product> productosFiltrados = new ArrayList<>();

        if (user != null) {
            if (user.getPreferencias() != null && !user.getPreferencias().isEmpty()) {
                for (Product producto : productos) {
                    if (user.getPreferencias().contains(producto.getCategoria()) && !user.getId().equals(producto.getUsuarioId())) {
                        productosFiltrados.add(producto);
                    }
                }
            } else {
                productosFiltrados.addAll(productos);
            }
        } else {
            // Manejar el caso en que el usuario no exista
        }

        return productosFiltrados;
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

    @PutMapping("/save")
    public Product updateProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
}


/*@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping("/save")
    public Product insertProduct(@RequestBody ProductImg product){
        System.out.println("entroooo");
        String path = "imgs/"+product.getNombre();
        File file = new File(path+".jpg");
        try {
            int i = 1;
            while(!file.createNewFile()){
                    file = new File(path+i+".jpg");
                    i++;
            }

            OutputStream os = new FileOutputStream(file,false);
            os.write(product.getImgBytes());
            product.setImgProducto(file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productService.saveProduct(new Product(product));
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }

    @PutMapping("/save")
    public Product updateProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
}*/
