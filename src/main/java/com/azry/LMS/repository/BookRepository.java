package com.azry.LMS.repository;

import com.azry.LMS.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.ISBN = :isbn")
    Optional<Book> findByISBN(@Param("isbn") String isbn);
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.ISBN = :isbn")
    boolean existsByISBN(@Param("isbn") String isbn);

}
