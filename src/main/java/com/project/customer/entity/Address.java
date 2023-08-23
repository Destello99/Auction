package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.customer.baseEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Address extends BaseEntity {
    private String address1;
    private String address2;
    private String street;
    private String city;
    private long pinCode;
    //multiple address can belong to one customer
    ////Bidirectional relation
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }
}
