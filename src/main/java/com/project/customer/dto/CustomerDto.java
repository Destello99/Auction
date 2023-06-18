package com.project.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {

    String firstName;
    String lastName;
    long phoneNumber;
    String email;
}
