package com.project.customer.controllers;

import java.time.LocalDate;
import java.util.List;

import com.project.customer.dto.ApiResponse;
import com.project.customer.service.CartServices;
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

    // for cart
    @Autowired
    private CartServices cartServices;

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

    // Methods for the cart
    @PostMapping("/cart/add_product")
    public ResponseEntity<?> addProductToCart(@RequestParam Integer quantity,@RequestParam Integer product_Id,@RequestParam Integer customer_Id){

        System.out.println(quantity+" "+product_Id+" "+" "+customer_Id);
        try{
        return new ResponseEntity<>(cartServices.addItem(quantity,product_Id,customer_Id),HttpStatus.OK);
        }catch (RuntimeException e){
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), LocalDate.now()));
        }
    }


}
