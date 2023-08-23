package com.project.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.customer.baseEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product  extends BaseEntity {


    private String name;
    private double price;
    private double highestBid;
    @Column(name = "EndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bidEndTime;

    private LocalDate addedDate;
    boolean status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer highestBidder;

}
