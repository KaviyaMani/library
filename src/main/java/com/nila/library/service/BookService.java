package com.nila.library.service;

import com.nila.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(Book book);
    Book updateBook(Long id, Book newBook);
    void deleteBook(Long id);
}
