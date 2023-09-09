package com.tadashi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    GreetResponse greet(){
        return new GreetResponse("<h1>Hey, This is sample greeting message!</h1>",List.of("Java",  "Kotlin", "JavaScript"),new Person("Sagar", 23, 8000));
    }

    record Person(String name, int age, double savings){}

    record GreetResponse(String greet, List<String> programmingLanguages, Person person){}

}
