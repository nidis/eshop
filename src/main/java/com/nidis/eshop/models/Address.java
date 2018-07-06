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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    private String type; //shipping or delivery

    private String street;

    private String city;

    private String postcode;
}
