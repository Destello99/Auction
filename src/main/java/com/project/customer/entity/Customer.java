package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.customer.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {
    private String firstName;
    private  String lastName;
    @Column(length = 10, name = "phoneNumbers",nullable = false)
    private  long phoneNumber;
    private String email;
    private short age;


    //customer can have many address
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Address> addresses = new ArrayList<>();

    //Customer has a wallet
    //Bi- directional relation
//    @OneToOne(mappedBy = "customer")
//    private  Wallet wallet;

    public Customer(String firstName, String lastName, long phoneNumber, String email, short age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public short getAge() {
        return age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonManagedReference
    public void addAddress(Address address){
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setCustomer(null);
    }
}
