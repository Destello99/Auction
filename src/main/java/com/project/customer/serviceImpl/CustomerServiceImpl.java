package com.project.customer.serviceImpl;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.AddressRepository;
import com.project.customer.repositories.CustomerRepository;
import com.project.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper mapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> getAllCustomers() {
        //fetching all the customer from DB
        return customerRepository.findAll();
    }


    @Override
    public Customer addCustomer(Customer customer) {
//        String password = customer.getPassword();
//        String encode = this.passwordEncoder.encode(password);
//        System.out.println(encode+" encoded pass");
//        customer.setPassword(encode);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByCustomerId(Integer id) {
        return customerRepository.findById(id).orElseThrow(()-> new NoSuchResourceFound("no customer available"));
    }
}

