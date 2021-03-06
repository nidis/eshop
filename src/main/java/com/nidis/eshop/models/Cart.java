package com.nidis.eshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id")
    @JsonIgnore
    private String sessionId;

    @Column(name = "ip_address")
    @JsonIgnore
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status")
    private String status;

    @Column(name = "time_created")
    @CreationTimestamp
    @JsonIgnore
    private Timestamp timeCreated;

    @OneToMany(mappedBy = "cartId")
    private List<CartItem> cartItems;
}