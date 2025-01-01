package com.mihir.simpleWebApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Product> getProducts() {
        // return products;
        return repo.findAll();
    }

    public Product getProductById(int proId) {
        // return products.stream()
        // .filter(p -> p.getProId() == proId).findFirst()
        // .orElse(new Product(100, "No Item", 0));
        return repo.findById(proId).orElse(new Product(proId, null, 0));
    }

    public void addNewProduct(Product pro) {
        // products.add(pro);
        repo.save(pro);
    }

    public void updateProduct(Product prod) {
        // int index = 0;
        // for (int i = 0; i < products.size(); i++) {
        // if (products.get(i).getProId() == prod.getProId())
        // index = i;
        // }

        // products.set(index, prod);
        repo.save(prod);
    }

    public void deleteProduct(int proId) {
        // int index = 0;
        // for (int i = 0; i < products.size(); i++) {
        // if (products.get(i).getProId() == proId)
        // index = i;
        // }

        // products.remove(index);

        repo.deleteById(proId);
    }
}
