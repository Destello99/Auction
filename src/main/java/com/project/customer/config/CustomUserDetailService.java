package com.project.customer.config;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchResourceFound("User not Found"));
    }
}
