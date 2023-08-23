package com.project.customer.custome_exception;

public class NoSuchResourceFound extends  RuntimeException{

    public  NoSuchResourceFound(String msg){
        super(msg);
    }
}
