package com.project.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class SignInDto {

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;
    //	@NotBlank
    @Length(min = 3,max=20,message = "Invalid password length")
    private String password;
}
