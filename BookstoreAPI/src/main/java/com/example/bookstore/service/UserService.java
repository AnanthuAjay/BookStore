package com.example.bookstore.service;

import java.util.Optional;

import com.example.bookstore.model.User;

public interface UserService {

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

}
