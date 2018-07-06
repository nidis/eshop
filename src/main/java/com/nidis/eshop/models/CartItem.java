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
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    private Long productId;

    private int quantity;
}
