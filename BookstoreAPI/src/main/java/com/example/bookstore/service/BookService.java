package com.example.bookstore.service;

import java.util.*;

import com.example.bookstore.model.Book;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategoryContainingIgnoreCase(String category);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

    Book save(Book book);

    void deleteById(Long id);
    public Book updateBook(Long id, Book book);


}
