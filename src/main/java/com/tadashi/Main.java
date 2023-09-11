package com.tadashi;

import com.tadashi.customer.Customer;
import com.tadashi.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer alex = new Customer(
                    "Alex",
                    "alex@gmail.com",
                    23
            );

            Customer sam = new Customer(
                    "Sam",
                    "sam@gmail.com",
                    23
            );

            List<Customer> customers = List.of(alex, sam);
            customerRepository.saveAll(customers);
        };
    }

}
