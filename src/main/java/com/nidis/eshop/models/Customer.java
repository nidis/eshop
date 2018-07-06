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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;
}
