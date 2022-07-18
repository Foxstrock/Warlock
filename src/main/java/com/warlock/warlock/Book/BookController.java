package com.warlock.warlock.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/getallbook")
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @GetMapping("/getbookdetails/{id}")
    public Book getBookDetails(@PathVariable("id") Integer id){
        if(bookRepository.findById(id).isPresent()) {
            return bookRepository.findById(id).get();
        }else{
            return new Book();
        }
    }

    @GetMapping("/getbookdetailsbyisbn/{isbn}")
    public Book getBookDetailsByIsbn(@PathVariable("isbn") String isbn){
        if(bookRepository.getBookByIsbn(isbn).isPresent()) {
            return bookRepository.getBookByIsbn(isbn).get();
        }else{
            return new Book();
        }
    }

    @PostMapping("/addnewbook")
    public Book addnewbook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/updatebook")
    public Book updatebook(@RequestBody Book book){
        Book newBook = new Book();
        newBook = bookRepository.findById(book.id).get();
        newBook = book;
        return bookRepository.save(newBook);
    }
}
