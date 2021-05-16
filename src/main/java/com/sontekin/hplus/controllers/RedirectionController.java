package com.sontekin.hplus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

    @GetMapping("/redirectToLinkedIn")
    public String goToLinkedIn(){
        System.out.println("in redirect controller");
        return "redirect:http://www.linkedin.com";
    }
}
