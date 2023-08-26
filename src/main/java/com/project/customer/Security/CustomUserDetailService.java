package com.project.customer.Security;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.entity.Customer;
import com.project.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

//Done as per madhura ma'am
@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = customerRepository.findByEmail(email)
                .orElseThrow(()-> new NoSuchResourceFound("Invalid Email"));
        return new CustomUserDetails(user);
    }
}
