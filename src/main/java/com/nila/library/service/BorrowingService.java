package com.nila.library.service;

import com.nila.library.entity.Book;
import com.nila.library.entity.Borrow;
import com.nila.library.entity.Reader;
import com.nila.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BorrowingService {

    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;
    @Autowired
    BorrowRepository borrowRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Borrow borrowBook(Long bookId, Long patronId) {
        Book book = bookService.getBookById(bookId);
        Reader patron = readerService.getReaderById(patronId);
        if (book.canBorrow()) {
            book.borrow();
            Borrow borrow = new Borrow(book, patron);
            borrowRepository.save(borrow);
            return borrow;
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The book: " +book.getTitle()+" is out of stock");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void returnBook(Long bookId, Long patronId) {
        Borrow borrow = findIfAlreadyBorrowed(bookId, patronId);
        borrow.doReturn();
        borrowRepository.save(borrow);
    }

    private Borrow findIfAlreadyBorrowed(Long bookId, Long patronId) {
        return borrowRepository.findIfAlreadyBorrowed(bookId, patronId).stream().findAny().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + bookId + " is not borrowed by patronId with id " + patronId));
    }

}
