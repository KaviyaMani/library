package com.nila.library.service.Impl;

import com.nila.library.entity.Book;
import com.nila.library.entity.Borrow;
import com.nila.library.entity.Reader;
import com.nila.library.repository.BorrowRepository;
import com.nila.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    BookServiceImpl bookServiceImpl;
    @Autowired
    ReaderServiceImpl readerServiceImpl;
    @Autowired
    BorrowRepository borrowRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Borrow borrowBook(Long bookId, Long readerId) {
        Book book = bookServiceImpl.getBookById(bookId);
        Reader reader = readerServiceImpl.getReaderById(readerId);
        if (book.canBorrow()) {
            book.borrow();
            Borrow borrow = new Borrow(book, reader);
            borrowRepository.save(borrow);
            return borrow;
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The book: " +book.getTitle()+" is out of stock");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void returnBook(Long bookId, Long readerId) {
        Borrow borrow = findIfAlreadyBorrowed(bookId, readerId);
        borrow.doReturn();
        borrowRepository.save(borrow);
    }

    private Borrow findIfAlreadyBorrowed(Long bookId, Long readerId) {
        return borrowRepository.findIfAlreadyBorrowed(bookId, readerId).stream().findAny().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + bookId + " is not borrowed by readerId with id " + readerId));
    }

}
