package com.tadashi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    GreetResponse greet(){
        return new GreetResponse("<h1>Hey, This is sample greeting message!</h1>");
    }

    record GreetResponse(String greet){
    }
}
