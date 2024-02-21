package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@RestController
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
    public Product insertProduct(@RequestBody Product product, byte[] img){
        String path = "imgs/"+product.getNombre();
        File file = new File(path+".png");
        try {
            int i = 1;
            while(!file.createNewFile()){
                    file = new File(path+i+".png");
                    i++;
            }

            OutputStream os = new FileOutputStream(file,false);
            os.write(img);
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
}
