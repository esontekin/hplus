package com.sontekin.hplus.controllers;

import com.sontekin.hplus.beans.Login;
import com.sontekin.hplus.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultValueForModelAttributeController {

    @ModelAttribute("newuser")
    public User getDefaultUser(){
        return new User();
    }

    @ModelAttribute("genderItems")
    public List<String> getGenderItems(){
        return Arrays.asList("Male", "Female", "Other");
    }

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }
}
