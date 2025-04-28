package com.example.ecommerce.service;

import com.example.ecommerce.model.Admin;

public interface AdminService {
    Admin signup(Admin admin);
    Admin login(String email, String password);
}
