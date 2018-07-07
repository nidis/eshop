package com.nidis.eshop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<Product> product;
}
