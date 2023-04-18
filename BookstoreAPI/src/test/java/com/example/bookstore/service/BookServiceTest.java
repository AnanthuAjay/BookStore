package com.example.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    
    private List<Book> bookList;
    //Book(Long id, String title, String author, String description, String category, double price)
    
    @Before
    public void setup() {
        bookList = new ArrayList<>();
        bookList.add(new Book(101L,"Book 1", "Author 1", "Book 1 description", "Book 1 category", 1000));
        bookList.add(new Book(102L,"Book 2", "Author 2", "Book 2 description", "Book 2 category", 2000));
        bookList.add(new Book(103L,"Book 3", "Author 3", "Book 3 description", "Book 3 category", 3000));
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.findAll();

        assertEquals(bookList.size(), result.size());
    }

    @Test
    public void testGetBookById() {
        Long id = 101L;
        Book book = new Book(101L,"Book 1", "Author 1", "Book 1 description", "Book 1 category", 1000);
        book.setId(id);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findById(id);

        assertEquals(book, result.get());
    }

    @Test
    public void testAddBook() {
        Book book = new Book(101L,"Book 1", "Author 1", "Book 1 description", "Book 1 category", 1000);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.save(book);

        assertEquals(book, result);
    }
    
   @Test
	public void testGetByTitleContainingIgnoreCase() {
	   String title = "Book 1";
       Book book = new Book(101L,title, "Author 1", "Book 1 description", "Book 1 category", 1000);
       book.setTitle(title);
       when(bookService.findByTitleContainingIgnoreCase(title)).thenReturn(bookRepository.findByTitleContainingIgnoreCase(title));

       List<Book> result = bookService.findByTitleContainingIgnoreCase(title);

       assertEquals(book, result);
		
	}

   	@Test
	public void testGetByAuthorContainingIgnoreCase() {
        
     // Arrange
        String author="Author 1";
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add( new Book(101L,"Book 1", author, "Book 1 description", "Book 1 category", 1000));
        expectedBooks.add( new Book(102L,"Book 2", author, "Book 2 description", "Book 2 category", 1200));
        expectedBooks.add( new Book(103L,"Book 3", author, "Book 3 description", "Book 3 category", 1030));
        expectedBooks.add( new Book(104L,"Book 4", author, "Book 4 description", "Book 4 category", 1500));
        bookRepository.saveAll(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.findByAuthorContainingIgnoreCase("Author 1");

        // Assert
        assertEquals(2, actualBooks.size()); // We expect 2 books with "John Doe" as author
        assertTrue(actualBooks.contains(expectedBooks.get(0)));
        assertTrue(actualBooks.contains(expectedBooks.get(2)));
	}

   	@Test
   	public void testFindByCategoryContainingIgnoreCase() {
   	    List<Book> books = bookService.findByCategoryContainingIgnoreCase("Book 3 category");
   	    assertEquals(1, books.size());
   	    assertTrue(books.stream().allMatch(book -> book.getCategory().toLowerCase().contains("Book 3 category")));
   	}


	@Test
	public void testFindByPriceBetween() {
	    List<Book> books = bookService.findByPriceBetween(1000D, 1500D);
	    assertEquals(4, books.size());
	    assertTrue(books.stream().allMatch(book -> book.getPrice() >= 1000D && book.getPrice() <= 1500D));
	}


	@Test
	public void deleteById() {
		// TODO Auto-generated method stub
		long id = 102L;
	    bookService.deleteById(id);
	    assertNull(bookService.findById(id));
	}

	@Test
	public void updateBook() {
		// TODO Auto-generated method stub
		 long id = 101L;
		    Book book = bookService.findById(id).get();
		    book.setPrice(35.0);
		    bookService.updateBook(id, book);
		    Book updatedBook = bookService.findById(id).get();
		    assertEquals(35.0, updatedBook.getPrice(), 0.0);
	}

}
