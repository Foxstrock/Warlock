package com.warlock.warlock.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book , Integer> {

    Optional<Book> getBookByIsbn(String isbn);
}
