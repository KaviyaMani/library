package com.nila.library.service;

import com.nila.library.entity.Book;
import com.nila.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;



@Service
@Transactional
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id "+id +" not found"));
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateBook(Long id, Book newBook) {

        Book book = getBookById(id);
        book.setTitle(Objects.toString(newBook.getTitle(), book.getTitle()));
        book.setDescription(Objects.toString(newBook.getDescription(), book.getDescription()));

        book.setIsbn(Objects.toString(newBook.getIsbn(), book.getIsbn()));
        book.setAuthor(Objects.toString(newBook.getAuthor(), book.getAuthor()));

        if (newBook.getPublicationYear() != null) {
            book.setPublicationYear(newBook.getPublicationYear());
        }
        if (newBook.getQuantity() != null) {
            book.setQuantity(newBook.getQuantity());
        }
        return bookRepository.save(book);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
