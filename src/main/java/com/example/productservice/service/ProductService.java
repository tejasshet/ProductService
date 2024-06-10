package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;


public interface ProductService {
    Product createProduct(Product product);

}
