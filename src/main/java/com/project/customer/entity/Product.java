package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Product  extends BaseEntity {
    String name;
    double price;
    LocalDate addedDate;
    boolean status=false;
    String img;
    String description;


    //Product : many, child , owning
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id" ,nullable = false ) //FK col name
//    private Category productCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
