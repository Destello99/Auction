package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Customer extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    private  String lastName;
    @Column(length = 10, name = "phoneNumbers",nullable = false)
    private  long phoneNumber;
    private String email;
    private short age;
    private String password;
    //customer can have many address
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Address> addresses = new ArrayList<>();
    //customer can have multiple roles
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =  @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    private Set<Roles> roles = new HashSet<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;
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

    public void removeRole(Roles role){
        roles.remove(role);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addCart(Cart cart){
        this.setCart(cart);
        cart.setCustomer(this);
    }

    //Method for the remove the cart
    public void removeCart(Cart cart){
        this.setCart(null);
        cart.setCustomer(null);
    }
}
