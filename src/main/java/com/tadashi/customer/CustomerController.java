package com.tadashi.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public void postCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        customerService.insertCustomer(customerRegistrationRequest);
    }

    @PutMapping("{customerId}")
    public void putCustomer(@PathVariable("customerId") int customerId, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        customerService.updateCustomer(customerId, customerUpdateRequest);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
