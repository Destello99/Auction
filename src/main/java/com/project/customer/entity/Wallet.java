package com.project.customer.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.customer.baseEntity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wallet extends  BaseEntity{
    private double money;

    @JsonProperty(access = Access.WRITE_ONLY)
    //Bidirectional relation
    @OneToOne
    @JoinColumn(name = "Customer_id")
    private Customer customer;
}

