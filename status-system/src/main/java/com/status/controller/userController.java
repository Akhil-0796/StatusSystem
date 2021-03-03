package com.status.controller;


import com.status.model.User;
import com.status.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class userController {

        @Autowired
        private UserRepo userRepo;

    @PostMapping("/addUser")
    public User save(@RequestBody User user){
        return userRepo.save(user);
    }

    @GetMapping("/users")
    public List<User> getusers(){
        return userRepo.findAll();
    }
}
