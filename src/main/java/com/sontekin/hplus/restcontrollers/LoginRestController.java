package com.sontekin.hplus.restcontrollers;

import com.sontekin.hplus.beans.Login;
import com.sontekin.hplus.beans.User;
import com.sontekin.hplus.exceptions.LoginFailureException;
import com.sontekin.hplus.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/hplus/rest/loginuser")
public class LoginRestController {

    private final UserRepository userRepository;

    public LoginRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity loginUser(@RequestBody Login login) throws LoginFailureException {
        System.out.println(login.getUsername()+" "+login.getPassword());
        Optional<User> user = userRepository.findByUsername(login.getUsername());
        if (!user.isPresent()){
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }

        if (user.get().getPassword().equals(login.getPassword())){
            return new ResponseEntity("Welcome "+user.get().getUsername(), HttpStatus.OK);
        }
        else{
            //throw new Exception
            throw new LoginFailureException("Invalid username or password");
        }
    }
}
