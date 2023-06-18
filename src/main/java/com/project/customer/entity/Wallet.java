//package com.project.customer.entity;
//
//import com.project.customer.baseEntity.BaseEntity;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.transaction.Transactional;
//
////@Embeddable
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//public class Wallet extends  BaseEntity{
//    private double money;
//
//    //Bidirectional relation
//    @OneToOne
//    @JoinColumn(name = "Customer_id")
//    private Customer customer;
//}
//
