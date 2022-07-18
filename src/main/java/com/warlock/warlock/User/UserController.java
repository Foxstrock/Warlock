package com.warlock.warlock.User;

import com.warlock.warlock.Book.Book;
import com.warlock.warlock.Book.BookRepository;
import com.warlock.warlock.Transiction.Transiction;
import com.warlock.warlock.Transiction.TransictionRepository;
import com.warlock.warlock.Utility.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransictionRepository translationRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/getalluser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/getuserdetails/{id}")
    public UserDto getUserDetails(@PathVariable("id") Integer id) {
         User user = userRepository.findById(id).get();

         List<Transiction> listTransiction = translationRepository.findAllByIdUser(id);
         List<Book> bookList = new ArrayList<Book>();

         listTransiction.forEach(transiction->{
             bookList.add(bookRepository.findById(transiction.getBook()).get());
         });


         UserDto dto = new UserDto();

         dto.setId(user.getId());
         dto.setName(user.getName());
         dto.setSurname(user.getSurname());
         dto.setBookList(bookList);

         return dto;
    }

    @PostMapping("/addnewuser")
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateuser/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id")Integer id){
       User oldUser = userRepository.findById(id).get();
       oldUser.setName(user.getName());
       oldUser.setSurname(user.getSurname());
       return userRepository.save(oldUser);
    }

    @PostMapping("/addbooktouser/{idUser}/{idBook}")
    public Transiction addBookTouser(@PathVariable("idUser")Integer idUser,@PathVariable("idBook")Integer idBook){
        Transiction transiction = new Transiction();

        transiction.setBook(idBook);
        transiction.setUser(idUser);
        transiction.setStatus(BookStatus.DISPONIBILE.toString());
        transiction.setDataIns(Date.from(Instant.now()));

        translationRepository.save(transiction);



        return transiction;
    }
    
}
