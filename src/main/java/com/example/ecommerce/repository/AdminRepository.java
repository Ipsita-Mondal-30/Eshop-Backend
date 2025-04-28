package main.java.com.example.ecommerce.repository;

import com.example.ecommerce.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}
