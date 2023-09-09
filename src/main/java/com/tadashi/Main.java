package com.tadashi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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

//    record GreetResponse(String greet){
//    }

    static class GreetResponse{
        private final String greet;

        GreetResponse(String greet){
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{" +
                    "greet='" + greet + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GreetResponse that)) return false;
            return Objects.equals(getGreet(), that.getGreet());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getGreet());
        }
    }
}
