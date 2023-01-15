package com.example.productstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProductStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductStoreApplication.class, args);
    }

    @GetMapping("/message")
    public String succesful(){
        return "welcome";
    }


}
