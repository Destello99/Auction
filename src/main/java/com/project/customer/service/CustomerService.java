package com.project.customer.service;

import com.project.customer.dto.CustomerDto;
import com.project.customer.entity.Customer;
import java.util.List;


public interface CustomerService {
    List<Customer> getAllCustomers();

    CustomerDto registerNewUser(CustomerDto customer);
    Customer addCustomer(Customer customer);

    Customer getByCustomerId(Integer id);

    void deleteCustomer(Integer id);
}
