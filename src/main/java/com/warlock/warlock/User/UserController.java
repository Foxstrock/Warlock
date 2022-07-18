package com.warlock.warlock.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getalluser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/getuserdetails/{id}")
    public User getUserDetails(@PathVariable("id") Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/addnewuser")
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    
}
