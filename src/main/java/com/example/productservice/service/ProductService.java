package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long productId);
}
