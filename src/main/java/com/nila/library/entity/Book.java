package com.nila.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Book title can not be null")
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Book author can not be null")
    private String author;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Book ISBN can not be null")
    private String isbn;

    @Column(name = "publication_year")
    @NotNull(message = "Book publication year can not be null")
    private Timestamp publicationYear;

    private Integer quantity = 0;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Borrow> borrows;

    public boolean canBorrow() {
        return quantity > 0;
    }

    public void borrow() {
        if (canBorrow()) {
            quantity--;
        }
    }

    public void returnBook() {
        quantity++;
    }
}
