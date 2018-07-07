package com.nidis.eshop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;
}
