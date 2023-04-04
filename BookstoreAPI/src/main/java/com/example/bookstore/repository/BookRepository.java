package com.example.bookstore.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategoryContainingIgnoreCase(String category);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

    @SuppressWarnings("unchecked")
	Book save(Book book);

    void deleteById(Long id);

}

