package com.project.customer.dto;

import com.project.customer.entity.CartItem;
import com.project.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {
    private Double totalCartPrice;
    private Set<CartItemDto> items = new HashSet<>();
}
