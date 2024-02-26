package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.*;
//import com.dam.truequeworld.models.ProductImg;
import com.dam.truequeworld.servicies.ProductService;
import com.dam.truequeworld.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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
                        productosFiltrados.add(setImg(producto));
                    }
                }
            } else {
                productosFiltrados.addAll(productos);
            }
        } else {
            // Manejar el caso en que el usuario no exista
        }
        for (int i = 0; i < productosFiltrados.size(); i++) {
            setImg(productosFiltrados.get(i));
        }
        return productosFiltrados;
    }

    @GetMapping("/buscar/{searchTerm}")
    public List<Product> buscarProductosPorNombreOCategoria(@PathVariable String searchTerm) {
        return productService.buscarPorNombreOCategoria(searchTerm);
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable Integer id){
        return setImg(productService.getProductById(id));
    }

    @PostMapping("/save")
    public Product insertProduct(@RequestBody Product product){
        String path = "imgs/"+product.getNombre();
        byte[] img = Base64.getMimeDecoder().decode(product.getImgProducto());
        File file = new File(path+".jpg");
        try {
            int i = 1;
            while(!file.createNewFile()){
                file = new File(path+i+".jpg");
                i++;
            }
            OutputStream os = new FileOutputStream(file,false);
            os.write(img);
            os.close();
            product.setImgProducto(file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    //RECUERDEN LLAMAR A ESTE METODO ANTES DE MANDERLE UN PRODUCTO AL CLIENTE
    public Product setImg(Product product){
        File file = new File(product.getImgProducto());
        try {
            byte[] bytesFile = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesFile);
            fis.close();
            product.setImgProducto(Base64.getMimeEncoder().encodeToString(bytesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @GetMapping("/estado/{estado}")
    public List<Product> getProductsByEstado(@PathVariable("estado") Integer estado) {
        List<Product> list = productService.getProductsByEstado(estado);
        for (int i = 0; i < list.size(); i++) {
            setImg(list.get(i));
        }
        return list;
    }
    @GetMapping("/products/propietario/{id}")
    public List<Product> getProductsUser(@PathVariable Integer id){
        List<Product> productos = productService.getProductsByUserId(id);
        for (int i = 0; i < productos.size(); i++) {
            setImg(productos.get(i));
        }
         return productos;
    }
}



