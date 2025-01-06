package com.mihir.simpleWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mihir.simpleWebApp.model.ApiResponse;
import com.mihir.simpleWebApp.model.Product;
import com.mihir.simpleWebApp.service.ProductService;
import java.util.*;

@RestController
public class ProductController {

    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{proId}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable int proId) {
        return service.getProductById(proId);
        // return service.getProductById(proId); stream api check
    }

    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> addNewProduct(@RequestBody Product pro) {
        return service.addNewProduct(pro);
    }

    @PutMapping("/products/{proId}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody Product prod) {
        return service.updateProduct(prod);
    }

    @DeleteMapping("/products/{proId}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable int proId) {
        return service.deleteProduct(proId);
        
    }
}
