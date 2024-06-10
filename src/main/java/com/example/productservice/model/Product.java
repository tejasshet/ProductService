package com.example.productservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

     @Column(name = "PRODUCT_NAME")
    private String productName;

     @Column(name = "PRODUCT_PRICE")
    private Double price;

     @Column(name = "QUANTITY")
    private Integer quantity;

}
