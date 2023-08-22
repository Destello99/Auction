package com.project.customer.dto;

import com.project.customer.entity.Cart;
import com.project.customer.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
public class CartItemDto {
    private Integer quantity;
    private Double totalPrice;
    private Product product;
}
