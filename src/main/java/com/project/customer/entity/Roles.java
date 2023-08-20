package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles  extends BaseEntity {

    @Column(name = "Role", nullable = false)
    //can't put null
   private String name;
    //many users have many roles
}
