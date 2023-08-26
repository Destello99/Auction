package com.project.customer.custome_exception;

public class JwtException extends RuntimeException{
    public JwtException(String msg){
        super(msg);
    }
}
