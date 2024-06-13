package com.example.productservice.controller;
import com.example.productservice.exception.DuplicateProductFoundException;
import com.example.productservice.exception.EmptyFieldException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (DuplicateProductFoundException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }catch (EmptyFieldException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    //To retrieve the details based on ID....
    @GetMapping("/product/{productId}")
        public ResponseEntity<?> getProductsById(@PathVariable Long productId) {
        try{
            Product product = productService.getProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch(ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }







}
