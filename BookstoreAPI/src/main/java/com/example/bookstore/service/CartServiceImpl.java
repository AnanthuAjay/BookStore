package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

	@Override
	public void addItemToCart(Long bookId, CartItem cartItem) {
		// TODO Auto-generated method stub
		Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));
		
		Cart cart = cartRepository.findById(cartItem.getId())
                .orElse(new Cart(cartItem.getId()));
		
		cart.addItem(book, cartItem.getQuantity());
        
        cartRepository.save(cart);
	}

	@Override
	public List<CartItem> getCartItemsByUserId(Long userId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found"));
        
        return cart.getCartItems();
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, CartItem cartItem) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartItem.getId())
                .orElseThrow(() -> new NotFoundException("Cart not found"));
        
        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cart item not found"));
        
        item.setQuantity(cartItem.getQuantity());
        
        cartRepository.save(cart);
		return item;
	}

}
