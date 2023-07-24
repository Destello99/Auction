package com.project.customer.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.customer.baseEntity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    private  String lastName;
    @Column(length = 10, name = "phoneNumbers",nullable = false)
    private  long phoneNumber;
    private String email;
    private short age;
    private String password;
    //customer can have many address
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Address> addresses = new ArrayList<>();

    //Customer has a wallet
    //Bi-directional relation
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private  Wallet wallet;

    public Customer(String firstName, String lastName, long phoneNumber, String email, short age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

//    @JsonManagedReference
    public void addAddress(Address address){
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setCustomer(null);
    }
    
    // WALLET HELPER METHODS
    public void addWallet(Wallet wallet) {
    	this.setWallet(wallet);
    	wallet.setCustomer(this);
    }
    
    public void removeWallet(Wallet wallet) {
    	this.setWallet(null);
    	wallet.setCustomer(null);
    }
}
