package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.customer.baseEntity.BaseEntity;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {
    private String address1;
    private String address2;
    private String street;
    private String city;
    private long pinCode;

    //multiple address can belong to one customer
    ////Bidirectional relation
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public long getPinCode() {
        return pinCode;
    }

    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }
}
