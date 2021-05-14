package com.shoryakaushik.urlShortnerBackend.controller;

import com.shoryakaushik.urlShortnerBackend.models.User;
import com.shoryakaushik.urlShortnerBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        repository.save(user);
        return "User Created Successfully";
    }

    @GetMapping("/get")
    public List<User> getUser() {
        return repository.findAll();
    }


}
