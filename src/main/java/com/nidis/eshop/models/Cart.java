package com.nidis.eshop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany()
    @JoinTable
    private Set<CartItem> cartItem;

    @Column(name = "status")
    private String status;
}
