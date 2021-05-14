package com.shoryakaushik.urlShortnerBackend.controller;

import com.shoryakaushik.urlShortnerBackend.models.LoggedUser;
import com.shoryakaushik.urlShortnerBackend.repository.LoggedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log-user")
public class LoggedUserController {

    @Autowired
    private LoggedUserRepository loggedUserRepository;

    @PostMapping("/create")
    public String createLoggedUser(@RequestBody LoggedUser loggedUser) {
        loggedUserRepository.save(loggedUser);
        return "User Created Successfully";
    }

    @GetMapping("/get")
    public List<LoggedUser> getLoggedUser() {
        return loggedUserRepository.findAll();
    }
}
