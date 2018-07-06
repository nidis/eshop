package com.nidis.eshop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private BigDecimal price;

    private boolean available;
}
