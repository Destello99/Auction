package com.project.customer.dto;

import com.project.customer.entity.CartItem;
import com.project.customer.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CartDto {

    private double totalCartPrice;
    private CustomerDto customer;

    private Set<CartItemDto> items = new HashSet<>();
}
