package com.project.customer.controllers;

import com.project.customer.dto.ApiResponse;
import com.project.customer.dto.CartDto;
import com.project.customer.repositories.WalletRepository;
import com.project.customer.service.AddressService;
import com.project.customer.service.CartServices;
import com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    AddressService addressService;

    @Autowired
    WalletRepository walletRepository;

    // for cart
    @Autowired
    private CartServices cartServices;




    @PostMapping("/addProduct/{productId}/{customerId}")
    public ResponseEntity<?> addProductToCart( @PathVariable Integer productId, @PathVariable Integer customerId){

        System.out.println(1+" "+productId+" "+" "+customerId);
        try{
            CartDto cartDto = cartServices.addItem(1,productId,customerId);
            System.out.println("Service returned");
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println("in catch block");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), LocalDate.now()));
        }
    }

    @GetMapping("/cartDetails/{customerId}")
    public ResponseEntity<?> getProductDetails(@PathVariable int customerId){
        try{
            return new ResponseEntity<>(cartServices.getCartDetails(customerId), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), LocalDate.now()));
        }
    }

    @PutMapping("/removeItem/{customerId}/{productId}")
    public ResponseEntity<?> removeItemsFromCart(@PathVariable Integer customerId, @PathVariable Integer productId){

        try{
            return new ResponseEntity<>(cartServices.removeItem(customerId,productId),HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),LocalDate.now()));
        }
    }
}
