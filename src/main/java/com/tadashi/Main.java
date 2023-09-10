package com.tadashi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class Main {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(
                1,
                "Alex",
                "alex@gmail.com",
                23
        );

        Customer sam = new Customer(
                2,
                "Sam",
                "sam@gmail.com",
                23
        );
        customers.add(alex);
        customers.add(sam);
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers(){
        return customers;
    }

    @GetMapping("api/v1/customers/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId){
        return customers.stream().filter(c -> c.id == customerId).findFirst().orElseThrow(()-> new IllegalArgumentException("customer with id [%s] not found".formatted(customerId)));
    }
    public static class Customer {
        private int id;
        private String name;
        private  String email;
        private int age;

        public Customer(){}

        public Customer(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Customer customer)) return false;
            return getId() == customer.getId() && getAge() == customer.getAge() && Objects.equals(getName(), customer.getName()) && Objects.equals(getEmail(), customer.getEmail());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName(), getEmail(), getAge());
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
