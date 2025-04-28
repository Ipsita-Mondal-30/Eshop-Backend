package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.example.ecommerce.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByCategory(String category);
}
