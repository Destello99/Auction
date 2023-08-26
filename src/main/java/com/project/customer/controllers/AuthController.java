package com.project.customer.controllers;

import com.project.customer.Security.CustomUserDetailService;
import com.project.customer.Security.JwtTokenHelper;
import com.project.customer.custome_exception.JwtException;
import com.project.customer.dto.CustomerDto;
import com.project.customer.dto.SignInDto;
import com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
//as per madhura ma'am
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody @Valid SignInDto request) throws Exception ,JwtException{
        Authentication principal = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String JwtToken = jwtTokenHelper.generateJwtToken(principal);
        return new ResponseEntity<>(new SignInDto(JwtToken, "user authenticate and token generated successfully"), HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws Exception, JwtException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("bad credential");
//            throw  new Exception("user name or password is wrong");
            throw new JwtException("user name or password is wrong");
        }

    }

    //Register New User
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerUser(@RequestBody CustomerDto customerDto){
        CustomerDto registeredUser = this.customerService.registerNewUser(customerDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
