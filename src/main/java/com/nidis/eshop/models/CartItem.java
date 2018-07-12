package com.nidis.eshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false,  insertable = false, updatable = false)
    private Long id;

    @Column(name = "cart_id")
    @JsonIgnore
    private Long cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long cartId, Product product, int quantity) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }
}
