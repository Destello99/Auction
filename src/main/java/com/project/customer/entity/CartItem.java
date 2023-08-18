package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    private int quantity;

    private double totalPrice;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

}
