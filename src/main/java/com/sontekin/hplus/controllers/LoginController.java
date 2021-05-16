package com.sontekin.hplus.controllers;

import com.sontekin.hplus.beans.Login;
import com.sontekin.hplus.beans.User;
import com.sontekin.hplus.exceptions.ApplicationException;
import com.sontekin.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AsyncTaskExecutor executor;

    @PostMapping("/login")
    public DeferredResult<String> login(@ModelAttribute("login") Login login, HttpServletRequest request) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        System.out.println("Async support in application: " + request.isAsyncSupported());
        System.out.println("Thread from the servlet container: " + Thread.currentThread().getName());

        executor.execute(()->{
            System.out.println("Thread from the spring mvc task executor: " + Thread.currentThread().getName());
            Optional<User> user = userRepository.findByUsername(login.getUsername());
            if (!user.isPresent()){
                deferredResult.setErrorResult(new ApplicationException("User not found"));
            }
            else {
                deferredResult.setResult("forward:/userprofile");
            }
        });

        return deferredResult;
    }

    @ExceptionHandler(ApplicationException.class)
    public String handleException() {
        System.out.println("in exception handler of Login Controller");
        return "error";
    }
}
