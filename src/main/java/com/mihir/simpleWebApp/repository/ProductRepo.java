package com.mihir.simpleWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mihir.simpleWebApp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}