package main.java.com.example.ecommerce.controller;

import com.example.ecommerce.model.User; // Assuming User is your model for both User and Admin
import com.example.ecommerce.service.UserService; // Assuming a UserService exists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody User loginRequest) {
        boolean isAdmin = userService.authenticateAdmin(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAdmin) {
            return ResponseEntity.ok("Admin logged in successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // Admin signup
    @PostMapping("/signup")
    public ResponseEntity<?> adminSignup(@RequestBody User userRequest) {
        boolean isAdminCreated = userService.registerAdmin(userRequest);
        if (isAdminCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating admin");
    }
}
