package com.project.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.customer.entity.Address;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.WalletRepository;
import com.project.customer.service.AddressService;
import com.project.customer.service.CustomerService;

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

    //Getting All customer
    @GetMapping
    public List<Customer> showAllCustomer(){
        //TODO not working
        return  customerService.getAllCustomers();
    }
    //Getting All customer done

    //getting customer by id
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return  customerService.getByCustomerId(id);
    }
    //getting customer by id done

    //adding customer
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
    //adding customer done

    //TODO delete customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
    }
    // deleting customer

    // Editing Customer details
    // Update Customer information
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,

                                                   @RequestParam("firstName") String firstName,
                                                   @RequestParam("lastName") String lastName,
                                                   @RequestParam("phoneNumber") long phoneNumber,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("age") short age) {
        // TODO: Call the updateCustomer method from the service implementation
        Customer updatedCustomer = customerService.updateCustomer(firstName, lastName, phoneNumber, email, age);

        // TODO: Set response status and return the updated customer
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    // Editing Customer details
}
