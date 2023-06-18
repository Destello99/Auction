package com.project.customer.controllers;

import com.project.customer.entity.Address;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.WalletRepository;
import com.project.customer.service.AddressService;
import com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    WalletRepository  walletRepository;

    @GetMapping
    public List<Customer> showAllCustomer(){
        //TODO not working
        return  customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return  customerService.getByCustomerId(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.addCustomer(customer);
        for(Address  add : customer.getAddresses()){
            add.setCustomer(customer);
            addressService.addAddress(add);
        }

        customer.getWallet().setCustomer(customer);
        walletRepository.save(customer.getWallet());
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
