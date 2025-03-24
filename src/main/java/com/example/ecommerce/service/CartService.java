// CartService.java
package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;

public interface CartService {
    Cart getCartBySessionId(String sessionId);
    
    Cart addItemToCart(String sessionId, String productId, Integer quantity);
    
    Cart updateCartItem(String sessionId, String productId, Integer quantity);
    
    Cart removeItemFromCart(String sessionId, String productId);
    
    void clearCart(String sessionId);
}