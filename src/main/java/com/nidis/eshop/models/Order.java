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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    private Long customerId;

    private Long timestamp;

    private Long deliveryId;

    private String status;
}
