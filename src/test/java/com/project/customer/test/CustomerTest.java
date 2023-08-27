package com.project.customer.test;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
// day 18.2 emps
@SpringBootTest
public class CustomerTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testGetAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println(customerList);
        assertEquals(4,customerList.size());
    }

    @Test
    void testAddCustomer(){
        Long phoneNo = Long.valueOf(1234567890);
        Customer customer = new Customer("Tushar","Satalkar",phoneNo,"tushar@123gmail.com",(short)23);
        assertEquals(customer,customerRepository.save(customer));
    }

    @Test
    void testGetCustomer(){
        Customer customer = customerRepository.findById(2).orElseThrow(()->new NoSuchResourceFound("Can't find the customer"));

        assertEquals("tushar",customer.getFirstName());
    }

//    @Test
//    void testUpdateCustomer(){
//        Customer
//    }
}
