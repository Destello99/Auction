package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    private int id;
    @Column(name = "Role", nullable = false)
   private String name;
    //many users have many roles
    //can't put null
}
