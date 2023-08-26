package com.project.customer.controllers;

import com.project.customer.entity.Address;
import com.project.customer.entity.Customer;
import com.project.customer.service.AddressService;
import com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    AddressService addressService;

    //Getting All customer, only ADMIN can access
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Customer> showAllCustomer(){
        return  customerService.getAllCustomers();
    }
    //Getting All customer done

    //getting customer by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return  customerService.getByCustomerId(id);
    }
    //getting customer by id done

    //adding customer
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.addCustomer(customer);
        for(Address  add : customer1.getAddresses()){
            add.setCustomer(customer1);
            addressService.addAddress(add);
        }
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
    //adding customer done
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Integer id){
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }
}
