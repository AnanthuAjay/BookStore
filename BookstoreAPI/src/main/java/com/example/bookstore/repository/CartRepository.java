package com.example.bookstore.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

    @SuppressWarnings("unchecked")
	Cart save(Cart cart);

    void deleteById(Long id);

}
