package com.warlock.Transiction;

import com.warlock.Book.Book;
import com.warlock.Book.BookRepository;
import com.warlock.User.User;
import com.warlock.User.UserRepository;
import com.warlock.Utility.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
        if(tr.status.equals("VENDUTO") || tr.status.equals("RESTITUITO") || tr.status.equals("PRENOTATO"))
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
    public List<Transiction> getTransictionsByidUsers(@PathVariable("idUser") Integer idUser,@PathVariable("idBook") Integer idBook){
        return transictionRepository.findTransictionByIdUserAndIdBook(idUser,idBook);
    }

    @GetMapping("/gettransictiofilter/{status}")
    public List<Transiction> getTransictionsfilter(@PathVariable("status")String status){
        return transictionRepository.findTransictionByStatus(status);
    }

    @GetMapping("/gettransictionbyprenotation/{prenotationname}")
    public List<Book> getprenotatedBook(@PathVariable("prenotationname")String prenotation){
        List<Transiction> list = transictionRepository.findAllByNamePrenotation(prenotation);
        List<Book> book = new ArrayList<Book>();

        list.forEach(it->{
            book.add(bookRepository.findById(it.getidBook()).get());
        });

        return book;
    }

    @GetMapping("/gettransictionbyprenotationbyuser/{prenotationname}")
    public Set<User> getprenotatedBooks(@PathVariable("prenotationname")String prenotation){
        List<Transiction> list = transictionRepository.findAllByNamePrenotation(prenotation);
        List<User> users = new ArrayList<User>();

        list.forEach(it->{
            users.add(userRepository.findById(it.getidUser()).get());
        });

        Set<User> set = users.stream().collect(Collectors.toSet());

        return set;
    }

    @PutMapping("/changetransictionlist")
    public List<Transiction> updateTransictionMassive(@RequestBody List<Transiction> list){

        List<Transiction> oldList = new ArrayList<Transiction>();

        list.forEach(it->{
            oldList.add(transictionRepository.findById(it.getIdTransiction()).get());
        });

        for(int i = 0; i < oldList.size(); i++){
            Transiction old = new Transiction();

            oldList.get(i).setStatus(list.get(i).getStatus());
            oldList.get(i).setNamePrenotation(list.get(i).getNamePrenotation());
        }

        transictionRepository.saveAll(oldList);

        return oldList;

    }
}
