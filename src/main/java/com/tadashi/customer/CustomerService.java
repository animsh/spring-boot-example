package com.tadashi.customer;

import com.tadashi.exception.DuplicateResourceException;
import com.tadashi.exception.RequestValidationException;
import com.tadashi.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id).orElseThrow(() -> new ResourceNotFound("customer with id [%s] not found".formatted(id)));
    }

    public void insertCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        if (customerDao.isCustomerWithEmailExists(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException("Customer with provided email already exists!");
        }

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(int id) {
        if (!customerDao.isCustomerWithIdExits(id)) {
            throw new ResourceNotFound("customer with id [%s] not found".formatted(id));
        }
        customerDao.deleteCustomerById(id);
    }

    public void updateCustomer(int id, CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomerById(id);
        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            customerDao.updateCustomer(customer);
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.isCustomerWithEmailExists(updateRequest.email())) {
                throw new DuplicateResourceException("Customer with provided email already exists!");
            }
            customer.setEmail(updateRequest.email());
            customerDao.updateCustomer(customer);
            changes = true;
        }

        if (updateRequest.age() != 0 && updateRequest.age() != customer.getAge()) {
            customer.setAge(updateRequest.age());
            customerDao.updateCustomer(customer);
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data change found!");
        }
    }
}
