package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.User;

public interface CartService {

    Optional<Cart> findByUser(User user);

    Cart save(Cart cart);

    void deleteById(Long id);

	void addItemToCart(Long bookId, CartItem cartItem);

	List<CartItem> getCartItemsByUserId(Long userId);

	CartItem updateCartItemQuantity(Long cartItemId, CartItem cartItem);

}
