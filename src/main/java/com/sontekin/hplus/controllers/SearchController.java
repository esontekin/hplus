package com.sontekin.hplus.controllers;

import com.sontekin.hplus.beans.Product;
import com.sontekin.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public Callable<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request){
        System.out.println("in search controller");
        System.out.println("search criteria: " + search);
        System.out.println("Async support in application: " + request.isAsyncSupported());
        System.out.println("Thread from the servlet container: " + Thread.currentThread().getName());

        return ()->{
            System.out.println("Thread from the spring mvc task executor: " + Thread.currentThread().getName());
            Thread.sleep(3000);
            List<Product> products = new ArrayList<>();
            products.addAll(productRepository.searchByName(search));
            model.addAttribute("products", products);
            return "search";
        };
    }
}
