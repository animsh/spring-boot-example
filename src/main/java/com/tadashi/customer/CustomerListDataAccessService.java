package com.tadashi.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {

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

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean isCustomerWithEmailExists(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean isCustomerWithIdExits(int id) {
        return customers.stream().anyMatch(c -> c.getId() == id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void deleteCustomerById(int id) {
        customers.removeIf(c -> c.getId() == id);
    }
}
