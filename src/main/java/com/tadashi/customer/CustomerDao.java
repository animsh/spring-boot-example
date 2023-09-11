package com.tadashi.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();

    Optional<Customer> getCustomerById(int id);

    void insertCustomer(Customer customer);

    boolean isCustomerWithEmailExists(String email);

    boolean isCustomerWithIdExits(int id);

    void updateCustomer(Customer customer);

    void deleteCustomerById(int id);
}
