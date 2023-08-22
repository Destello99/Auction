package com.project.customer.service;

import com.project.customer.entity.Customer;
import java.util.List;


public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer);

    Customer getByCustomerId(Integer id);
    void deleteCustomer(int id);
    Customer updateCustomer(String firstName, String lastName, long phoneNumber, String email, short age);
}


