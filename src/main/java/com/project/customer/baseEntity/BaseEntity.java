package com.project.customer.baseEntity;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

}
