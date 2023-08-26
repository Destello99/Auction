package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item extends BaseEntity {
    private String name;
    private double price;
    private LocalDate addedDate;
    private boolean status;
    private byte[] img;

    @Version
    private Long version=0l;

}
