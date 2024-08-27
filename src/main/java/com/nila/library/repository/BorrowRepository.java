package com.nila.library.repository;

import com.nila.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    @Query("select b from Borrow b where b.book.id = :bookId and b.reader.id = :readerId and b.isReturned = false")
    List<Borrow> findIfAlreadyBorrowed(@Param("bookId") Long bookId, @Param("readerId") Long readerId);

}
