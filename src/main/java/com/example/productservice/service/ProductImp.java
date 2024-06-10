package com.example.productservice.service;
import com.example.productservice.exception.DuplicateProductFoundException;
import com.example.productservice.exception.EmptyFieldException;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Deprecated
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
        if(product.getPrice()==null){
            throw new EmptyFieldException("Product Price must not be empty");
        }
        if(product.getQuantity()==null){
            throw new EmptyFieldException("Product Quantity must not be empty");
        }
        return productRepository.save(product);
    }
}
