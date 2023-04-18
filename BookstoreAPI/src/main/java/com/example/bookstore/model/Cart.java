package com.example.bookstore.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.example.bookstore.repository.CartRepository;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }

	public Cart(Long id2) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addItem(Book book, int quantity) {
	    CartItem cartItem = new CartItem(book, quantity);

	    cartItems.add(cartItem);
	}


    
}
