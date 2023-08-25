package com.project.customer.exception_handler;

import java.time.LocalDate;
import java.util.List;

import com.project.customer.custome_exception.NoSuchResourceFound;
import com.project.customer.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // =@COntrollerAdvice => global Exc handler class
//--common interpretor to interrupt All exc in all Controller + @ResponseBody added on ret type of ret type of all request handling method
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException
            (MethodArgumentNotValidException e){
        System.out.println("in method arg invalid "+e);
        List<FieldError> fieldErrors = e.getFieldErrors();// list of fields having validation errors

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), LocalDate.now()));
    }

    @ExceptionHandler(NoSuchResourceFound.class)
    public ResponseEntity<?> handlerNoSuchResourceFound(NoSuchResourceFound e){
        System.out.println("in noSuchResourceFound method "+e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),LocalDate.now()));
    }

}
