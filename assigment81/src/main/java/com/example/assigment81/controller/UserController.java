package com.example.assigment81.controller;

import com.example.assigment81.model.User;
import com.example.assigment81.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{Id}")
    public User createById(@PathVariable Integer Id) {
        userService.createUser();
        return userService.returnAl(1);
    }

    @PostMapping("/users/3stream")
    public void create3Streams() {
        userService.stream3();
    }

    @GetMapping("/users/filterage")
    public List<User> filterAge() {
        return userService.under18();
    }

    @GetMapping("/users/doubleage")
    public List<User> doubleTheAge() {
        return userService.doubleAge();
    }

    @GetMapping("/users/lastuser")
    public User lastUser() {
        return userService.lastelement();
    }

    @GetMapping("/users/smallandbig")
    public List<User> opposites() {
        return userService.smallestage();
    }

    @DeleteMapping("/users/deletesame")
    public List<User> different() {
        return userService.distinct();
    }

    @GetMapping("/users/filtestuff")
    public List<User> filterStuff() {
        return userService.hardPart();
    }


}
