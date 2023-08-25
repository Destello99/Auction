package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.project.customer.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cart")
public class CartItem extends BaseEntity {

    private Integer quantity;

    private Double totalPrice;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

}
