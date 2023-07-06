package com.project.customer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ProductDto {
    private String name;
    private double price;
    private String description;
    private MultipartFile imageFile;
}
