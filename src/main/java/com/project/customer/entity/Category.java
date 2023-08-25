package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Category extends BaseEntity {
//
//    @Column(length = 30,unique = true)
//    private String categoryName;
//
//    //Category : one , parent , non owning(inverse)
//    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Product> products = new ArrayList<>();
//
//    // Helper methods for the product addition and removal
//    public void addProduct(Product product){
//        product.setProductCategory(this);
//        products.add(product);
//    }
//
//    public void removeProduct(Product product){
//        product.setProductCategory(null);
//        products.remove(product);
//    }
//
//}
