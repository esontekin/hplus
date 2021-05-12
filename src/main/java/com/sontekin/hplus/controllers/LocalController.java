package com.sontekin.hplus.controllers;

import com.sontekin.hplus.beans.Login;
import com.sontekin.hplus.beans.User;
import com.sontekin.hplus.exceptions.ApplicationException;
import com.sontekin.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LocalController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login) {
        Optional<User> user = userRepository.findByUsername(login.getUsername());
        if (!user.isPresent()){
            throw new ApplicationException("User not found");
        }
        return "search";
    }

    @ExceptionHandler(ApplicationException.class)
    public String handleException() {
        System.out.println("in exception handler of Login Controller");
        return "error";
    }
}
