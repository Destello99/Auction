package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.customer.baseEntity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {
    private String address;

    private String street;
    private String city;
    private long pinCode;

    //multiple address can belong to one customer
    ////Bidirectional relation
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public String getAddress1() {
        return address;
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
