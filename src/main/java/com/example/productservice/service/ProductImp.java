package com.example.productservice.service;
import com.example.productservice.exception.DuplicateProductFoundException;
import com.example.productservice.exception.EmptyFieldException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductImp implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsByProductName(product.getProductName())) {
            throw new DuplicateProductFoundException("Product Name Found in the database");
        }
        if(StringUtils.isEmpty(product.getProductName())){
            throw new EmptyFieldException("Product name must not be empty");
        }
        if(product.getPrice()==null || product.getPrice() <= 0){
            throw new EmptyFieldException("Product Price must not be null or empty or less than 0");
        }
        if(product.getQuantity()==null || product.getQuantity() <= 0){
            throw new EmptyFieldException("Product Quantity must not be null or empty or less than 0");
        }
        return productRepository.save(product);
    }

    //to fetch all the products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //to fetch product based on ID
    public Product getProductById(Long productId){
        Product product = productRepository.findById(productId).orElse(null);
           if(product == null){
               throw new ProductNotFoundException("Product Doesn't exist");
           }
           return product;
    }
}
