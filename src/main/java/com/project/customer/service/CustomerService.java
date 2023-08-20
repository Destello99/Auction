package com.project.customer.service;

import com.project.customer.entity.Customer;
import java.util.List;


public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer);

    Customer getByCustomerId(Integer id);
}
