package com.mihir.simpleWebApp.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mihir.simpleWebApp.model.ApiResponse;
import com.mihir.simpleWebApp.model.Product;
import com.mihir.simpleWebApp.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    // List<Product> products = new ArrayList<>(Arrays.asList(
    // new Product(101, "Trimmer", 2000),
    // new Product(102, "IPhone", 70000),
    // new Product(103, "Mouse", 700)));

    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        List<Product> product = repo.findAll();
        ApiResponse<List<Product>> response = new ApiResponse<>("Data Successfully fetched", product,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Product>> getProductById(int proId) {
        // return products.stream()
        // .filter(p -> p.getProId() == proId).findFirst()
        // return service.getProductById(proId); stream api check
        // .orElse(new Product(100, "No Item", 0));
        // return repo.findById(proId).orElse(null);

        Product product = repo.findById(proId).orElse(null);
        if (product == null) {
            ApiResponse<Product> response = new ApiResponse<>("Product Not Found", product,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ApiResponse<Product> response = new ApiResponse<>("Product Successfully Found", product,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Product>> addNewProduct(Product pro) {
        Product savedProduct = repo.save(pro);
        ApiResponse<Product> response = new ApiResponse<>(
                "Product Successfully Added",
                savedProduct,
                HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse<Product>> updateProduct(Product prod) {
        // int index = 0;
        // for (int i = 0; i < products.size(); i++) {
        // if (products.get(i).getProId() == prod.getProId())
        // index = i;
        // }
        // products.set(index, prod);
        Product product = repo.save(prod);
        ApiResponse<Product> response = new ApiResponse<>(
                "Product Successfully Updated",
                product,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Product>> deleteProduct(int proId) {
        Product product = repo.findById(proId).orElse(null);
        System.out.println(product);
        if (product == null) {
            ApiResponse<Product> response = new ApiResponse<Product>("Product not found", product,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        repo.deleteById(proId);
        ApiResponse<Product> response = new ApiResponse<>(
                "Product successfully deleted",
                product,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
