package main.java.com.example.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin {
    
    @Id
    private String id;
    
    private String email;
    
    private String password;

    // Getters and Setters
}
