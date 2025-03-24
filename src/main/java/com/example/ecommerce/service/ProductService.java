// ProductService.java
package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    
    Product getProductById(String id);
    
    Product createProduct(Product product);
    
    Product updateProduct(String id, Product product);
    
    void deleteProduct(String id);
    
    List<Product> searchProducts(String keyword);
}