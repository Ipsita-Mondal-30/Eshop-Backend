package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")  // For React frontend
public class CartController {
    
    private final CartService cartService;
    
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping("/{sessionId}")
    public ResponseEntity<Cart> getCart(@PathVariable String sessionId) {
        Cart cart = cartService.getCartBySessionId(sessionId);
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/{sessionId}/items")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable String sessionId,
            @RequestParam String productId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        Cart updatedCart = cartService.addItemToCart(sessionId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
    
    @PutMapping("/{sessionId}/items/{productId}")
    public ResponseEntity<Cart> updateCartItem(
            @PathVariable String sessionId,
            @PathVariable String productId,
            @RequestParam Integer quantity) {
        Cart updatedCart = cartService.updateCartItem(sessionId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
    
    @DeleteMapping("/{sessionId}/items/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(
            @PathVariable String sessionId,
            @PathVariable String productId) {
        Cart updatedCart = cartService.removeItemFromCart(sessionId, productId);
        return ResponseEntity.ok(updatedCart);
    }
    
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> clearCart(@PathVariable String sessionId) {
        cartService.clearCart(sessionId);
        return ResponseEntity.noContent().build();
    }
}