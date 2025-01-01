package com.mihir.simpleWebApp.model;

// import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {

    @jakarta.persistence.Id
    private int proId;
    private String proName;
    private int proPrice;

    public Product() {
    }
}
