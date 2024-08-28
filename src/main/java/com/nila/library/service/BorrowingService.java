package com.nila.library.service;

import com.nila.library.entity.Borrow;

public interface BorrowingService {

    Borrow borrowBook(Long bookId, Long readerId);
    void returnBook(Long bookId, Long readerId);
}
