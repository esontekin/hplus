package com.sontekin.hplus.restcontrollers;

import com.sontekin.hplus.beans.Product;
import com.sontekin.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/hplus/rest/products")
public class ProductsRestController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductsRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   /* @GetMapping("/hplus/rest/products")
    @ResponseBody
    public List<Product> getProducts(){
        //call product repo
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }*/

    @GetMapping()
    public ResponseEntity getProductsByRequestParam(@RequestParam("name") String name){
        List<Product> products = productRepository.searchByName(name);
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductsById(@PathVariable("id") int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
