package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userid;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productid;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "deleted")
    private boolean deleted = false;
}
