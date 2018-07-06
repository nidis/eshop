package com.nidis.eshop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private int quantity;
}
