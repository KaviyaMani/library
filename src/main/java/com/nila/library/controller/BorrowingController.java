package com.nila.library.controller;

import com.nila.library.entity.Borrow;
import com.nila.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/reader/{readerId}")
    public Borrow borrowBook(@PathVariable Long bookId, @PathVariable Long readerId) {
        return borrowingService.borrowBook(bookId, readerId);
    }

    @PutMapping("/return/{bookId}/reader/{readerId}")
    public void returnBook(@PathVariable Long bookId, @PathVariable Long readerId) {
        borrowingService.returnBook(bookId, readerId);
    }

}
