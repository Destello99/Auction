package com.project.customer.entity;

import com.project.customer.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
   private UserRole roleName;
    //many users have many roles
    //can't put null
}
