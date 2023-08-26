package com.project.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ProductDto {
    String name;
    double price;
    LocalDate addedDate;
    boolean status;
//    byte[] img;
}
