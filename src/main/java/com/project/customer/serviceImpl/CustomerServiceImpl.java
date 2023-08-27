package com.project.customer.serviceImpl;

import com.project.customer.config.AppConstants;
import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.CustomerDto;
import com.project.customer.entity.Customer;
import com.project.customer.entity.Roles;
import com.project.customer.repositories.AddressRepository;
import com.project.customer.repositories.CustomerRepository;
import com.project.customer.repositories.RoleRepository;
import com.project.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Customer> getAllCustomers() {
        //fetching all the customer from DB
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        //TODO
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByCustomerId(Integer id) {
        return customerRepository.findById(id).orElseThrow(()-> new NoSuchResourceFound("no customer avilable"));
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer=customerRepository.findById(id).orElseThrow(()->new NoSuchResourceFound("customer not found"));
        customerRepository.delete(customer);
    }


    @Override
    public Customer updateCustomer(String firstName, String lastName, long phoneNumber, String email, short age) {
        // TODO: Implement the logic to update customer information

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setAge(age);

        // TODO: Call repository or update method to persist the changes

        return customer;
    }
    @Override
    public CustomerDto registerNewUser(CustomerDto customerDto) {
        Customer customer = this.mapper.map(customerDto, Customer.class);
        //encoded password
        customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
        //set roles
        Roles role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        customer.getRoles().add(role);
        Customer NewUser = this.customerRepository.save(customer);
        return this.mapper.map(NewUser, CustomerDto.class);
    }
}
