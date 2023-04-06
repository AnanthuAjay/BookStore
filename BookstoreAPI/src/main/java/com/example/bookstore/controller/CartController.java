package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.CartItem;
import com.example.bookstore.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/{bookId}")
    public void addItemToCart(@PathVariable Long bookId, @RequestBody CartItem cartItem) {
        cartService.addItemToCart(bookId, cartItem);
    }
    
    @GetMapping("/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }
    
    @PutMapping("/{cartItemId}")
    public CartItem updateCartItemQuantity(@PathVariable Long cartItemId, @RequestBody CartItem cartItem) {
        return cartService.updateCartItemQuantity(cartItemId, cartItem);
    }
    
    @DeleteMapping("/{cartItemId}")
    public void removeCartItem(@PathVariable Long cartItemId) {
    	cartService.deleteById(cartItemId);
    }
}
