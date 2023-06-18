package com.project.customer.service;

import com.project.customer.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer);

    Customer getByCustomerId(Integer id);
}
