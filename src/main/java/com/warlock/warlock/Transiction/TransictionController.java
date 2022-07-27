package com.warlock.warlock.Transiction;

import com.warlock.warlock.Book.Book;
import com.warlock.warlock.Book.BookRepository;
import com.warlock.warlock.User.User;
import com.warlock.warlock.User.UserRepository;
import com.warlock.warlock.Utility.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/transiction")
public class TransictionController {

    @Autowired
    TransictionRepository transictionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/addbooktouser/{idUser}/{idBook}")
    public Transiction addBookTouser(@PathVariable("idUser")Integer idUser, @PathVariable("idBook")Integer idBook){
        Transiction transiction = new Transiction();

        transiction.setBook(idBook);
        transiction.setUser(idUser);
        transiction.setStatus(BookStatus.DISPONIBILE.toString());
        transiction.setDataIns(Date.from(Instant.now()));

        transictionRepository.save(transiction);



        return transiction;
    }

    @GetMapping("/getbookfromuser/{id}")
    public List<Book> getBooksFromUser(@PathVariable("id") Integer id) {
        List<Transiction> transictionList = transictionRepository.findTransictionByIdUser(id);
        List<Book> books = new ArrayList<Book>();

        transictionList.forEach( it -> {
            Book book = new Book(bookRepository.findById(it.getidBook()).get(), it.getStatus(),it.idTransiction);
            books.add(book);
        });


        return books;
    }

    @GetMapping("/getuserfrombook/{id}")
    public List<User> getUserFromBook(@PathVariable("id")Integer id){
        List<Integer> UsersId = transictionRepository.findUserIdFromBookId(id);
        List<User> users = new ArrayList<User>();

        UsersId.forEach(userId ->{
            users.add(userRepository.findById(userId).get());
        });

        return users;
    }

    @PutMapping("/updatetransiction")
    public Transiction updateTransiction(@RequestBody Transiction tr){
        Transiction newTr = transictionRepository.getReferenceById(tr.idTransiction);

        newTr.setStatus(tr.status);
        if(tr.status.equals("VENDUTO"))
        newTr.setDateUsc(Date.from(Instant.now()));

        newTr.setNamePrenotation(tr.getNamePrenotation());
        newTr.setPrezzoVendita(tr.prezzoVendita);

        return transictionRepository.save(newTr);
    }

    @GetMapping("/gettransictiondetails/{id}")
    public Transiction getTransictionDetails(@PathVariable("id")Integer id) {
        return transictionRepository.findById(id).get();
    }

    @GetMapping("/gettransictionbyids/{idUser}/{idBook}")
    public List<Transiction> getTransictionsByidUsers(Integer idUser,Integer idBook){
        return transictionRepository.findTransictionByIdUserAndIdBook(idUser,idBook);
    }
}
