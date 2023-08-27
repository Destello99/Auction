package com.project.customer.dto;

import com.project.customer.entity.Roles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class CustomerDto {

    @NotEmpty
    String firstName;
    String lastName;
    long phoneNumber;
    @Email(message = "Email address ia not valid")
    String email;
    @NotEmpty
    @Size(min = 3,max = 10, message = "password must be 3 character and max 10 character")
    String password;
    private Set<RolesDto> roles = new HashSet<>();
    private int age;
}
