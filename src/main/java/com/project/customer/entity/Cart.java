package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Cart extends BaseEntity {

    private Double totalCartPrice;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

public void addItems(CartItem item){
    items.add(item);
    item.setCart(this);
}

public void removeItems(CartItem item){
    items.remove(item);
    item.setCart(null);
}
}
