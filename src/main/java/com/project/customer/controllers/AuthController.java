package com.project.customer.controllers;

import com.project.customer.config.CustomUserDetailService;
import com.project.customer.config.JwtTokenHelper;
import com.project.customer.custome_exception.JwtException;
import com.project.customer.dto.CustomerDto;
import com.project.customer.dto.JwtAuthRequest;
import com.project.customer.dto.JwtAuthResponse;
import com.project.customer.service.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    //http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@NotNull @RequestBody JwtAuthRequest request){
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(request.getUsername());
        String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(generatedToken);
        return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws JwtException{
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("bad credential");
            throw new JwtException("user name or password is wrong");
        }

    }

    //Register New User
    //http://localhost:8080/api/auth/register
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerUser(@RequestBody CustomerDto customerDto){
        CustomerDto registeredUser = this.customerService.registerNewUser(customerDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
