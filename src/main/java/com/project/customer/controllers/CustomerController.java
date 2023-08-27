package com.project.customer.controllers;

import java.time.LocalDate;
import java.util.List;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.ApiResponse;
import com.project.customer.entity.Roles;
import com.project.customer.repositories.AddressRepository;
import com.project.customer.repositories.CustomerRepository;
import com.project.customer.repositories.RoleRepository;
import com.project.customer.service.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.project.customer.entity.Address;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.WalletRepository;
import com.project.customer.service.AddressService;
import com.project.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    AddressService addressService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // for cart
    @Autowired
    private CartServices cartServices;

    //Getting All customer
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    //http://localhost:8080/customer
    public List<Customer> showAllCustomer(){
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
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    //http://localhost:8080/customer/add
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.addCustomer(customer);
        for(Address  add : customer1.getAddresses()){
            add.setCustomer(customer1);
            addressService.addAddress(add);
        }
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

    //TODO deleting customer (foreign key constraint)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    //http://localhost:8080/customer/delete/{id}
    public ResponseEntity<?> deleteCustomerById(@PathVariable Integer id){
        Customer byId = this.customerRepository.findById(id).orElseThrow(()-> new NoSuchResourceFound("no user with this id"));
        for(Roles role : byId.getRoles()){
            System.out.println(role);
            this.roleRepository.delete(role);
        }
        for(Address address : byId.getAddresses()){
            this.addressRepository.delete(address);
        }
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int id,

                                                   @RequestParam("firstName") String firstName,
                                                   @RequestParam("lastName") String lastName,
                                                   @RequestParam("phoneNumber") long phoneNumber,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("age") short age) {

        Customer updatedCustomer = customerService.updateCustomer(firstName, lastName, phoneNumber, email, age);


        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
