package com.warlock.User;

import com.warlock.Book.Book;
import com.warlock.Book.BookRepository;
import com.warlock.Transiction.Transiction;
import com.warlock.Transiction.TransictionRepository;
import com.warlock.Utility.BookStatus;
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


    @GetMapping("/getalluser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/getuserdetails/{id}")
    public User getUserDetails(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();

       return user;
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


}
