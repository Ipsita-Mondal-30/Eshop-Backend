// CartServiceImpl.java
package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    
    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
    
    @Override
    public Cart getCartBySessionId(String sessionId) {
        return getOrCreateCart(sessionId);
    }
    
    @Override
    public Cart addItemToCart(String sessionId, String productId, Integer quantity) {
        Cart cart = getOrCreateCart(sessionId);
        
        // Find the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        // Check if item already exists in cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
        
        if (existingItem.isPresent()) {
            // Update quantity if item already exists
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);
            cartItem.setImageUrl(product.getImageUrl());
            cart.addItem(cartItem);
        }
        
        // Save cart
        return cartRepository.save(cart);
    }
    
    @Override
    public Cart updateCartItem(String sessionId, String productId, Integer quantity) {
        Cart cart = getOrCreateCart(sessionId);
        
        // Find the cart item
        Optional<CartItem> cartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
        
        if (cartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found with product id: " + productId);
        }
        
        // Update quantity
        cartItem.get().setQuantity(quantity);
        
        // Save cart
        return cartRepository.save(cart);
    }
    
    @Override
    public Cart removeItemFromCart(String sessionId, String productId) {
        Cart cart = getOrCreateCart(sessionId);
        
        // Find the cart item
        Optional<CartItem> cartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
        
        if (cartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found with product id: " + productId);
        }
        
        // Remove item from cart
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        
        // Save cart
        return cartRepository.save(cart);
    }
    
    @Override
    public void clearCart(String sessionId) {
        Cart cart = getOrCreateCart(sessionId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
    
    // Helper methods
    private Cart getOrCreateCart(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setSessionId(sessionId);
                    return cartRepository.save(newCart);
                });
    }
}