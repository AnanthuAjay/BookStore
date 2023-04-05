package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
    private BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> findByTitleContainingIgnoreCase(String title) {
		// TODO Auto-generated method stub
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<Book> findByAuthorContainingIgnoreCase(String author) {
		// TODO Auto-generated method stub
		return bookRepository.findByAuthorContainingIgnoreCase(author);
	}

	@Override
	public List<Book> findByCategoryContainingIgnoreCase(String category) {
		// TODO Auto-generated method stub
		return bookRepository.findByCategoryContainingIgnoreCase(category);
	}

	@Override
	public List<Book> findByPriceBetween(Double minPrice, Double maxPrice) {
		// TODO Auto-generated method stub
		return bookRepository.findByPriceBetween(minPrice, maxPrice);
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);;
	}

	@Override
	public Book updateBook(Long id, Book book) {
		// TODO Auto-generated method stub
		Optional<Book> foundBook=bookRepository.findById(id);
		if(foundBook.isPresent()) {
			Book existingBook=foundBook.get();
			existingBook.setId(book.getId());
			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setDescription(book.getDescription());
			return bookRepository.save(existingBook);
		}else {
            throw new NotFoundException("Book not found with id " + id);
        }
	}

}
