package com.mihir.simpleWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mihir.simpleWebApp.model.Product;
import com.mihir.simpleWebApp.service.ProductService;
import java.util.*;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{proId}")
    public Product getProductById(@PathVariable int proId) {
        return service.getProductById(proId);
    }

    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product pro) {
        service.addNewProduct(pro);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product prod) {
        service.updateProduct(prod);
    }

    @DeleteMapping("/products/{proId}")
    public void deleteProduct(@PathVariable int proId) {
        service.deleteProduct(proId);
    }

}
