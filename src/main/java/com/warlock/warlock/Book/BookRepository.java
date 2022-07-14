package com.warlock.warlock.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book , String> {

    Optional<Book> getBookByIsbn(String isbn);

}
