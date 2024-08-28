package com.nila.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = false)
    private Reader reader;

    @Column(name = "borrowing_date")
    @NotNull
    private Timestamp borrowingDate;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @Column(name = "is_returned")
    private boolean isReturned;

    public Borrow(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.borrowingDate = new Timestamp(System.currentTimeMillis());
    }

    public void doReturn() {
        setReturned(true);
        setReturnDate(new Timestamp(System.currentTimeMillis()));
        book.returnBook();
    }
}
