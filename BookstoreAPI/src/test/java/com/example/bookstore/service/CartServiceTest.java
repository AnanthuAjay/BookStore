package com.example.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;

@SpringBootTest
public class CartServiceTest {

    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private BookRepository bookRepository;

    

    @Test
    public void testAddItemToCart() {
        Long bookId = 1L;
        Integer quantity = 2;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setPrice(10.0);

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(quantity);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(new User());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(cartRepository.findById(cartItem.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        cartService.addItemToCart(bookId, cartItem);

        assertEquals(1, cart.getCartItems().size());
        assertNotNull(cart.getCartItems().get(0));
        assertEquals(book, cart.getCartItems().get(0).getBook());
        assertEquals(quantity, cart.getCartItems().get(0).getQuantity());
    }

    @Test
    public void testGetCartItemsByUserId() {
        Long userId = 1L;

        Cart cart = new Cart();
        cart.setId(userId);
        cart.setUser(new User());

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        cartItems.add(new CartItem());
        cartItems.add(new CartItem());

        cart.setCartItems(cartItems);

        when(cartRepository.findById(userId)).thenReturn(Optional.of(cart));

        List<CartItem> result = cartService.getCartItemsByUserId(userId);

        assertEquals(3, result.size());
    }

    @Test
    public void testUpdateCartItemQuantity() {
        Long cartItemId = 1L;
        Integer newQuantity = 3;

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(newQuantity);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(new User());

        List<CartItem> cartItems = new ArrayList<>();
        CartItem item = new CartItem();
        item.setId(cartItemId);
        item.setQuantity(2);
        item.setBook(new Book());
        cartItems.add(item);

        cart.setCartItems(cartItems);

        when(cartRepository.findById(cartItem.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        CartItem result = cartService.updateCartItemQuantity(cartItemId, cartItem);

        assertEquals(cartItemId, result.getId());
        assertEquals(newQuantity, result.getQuantity());
    }
}
