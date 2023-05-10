package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column (name = "description")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory categoryid;

    @Column(name = "brand")
    private String brand;

    @OneToOne
    @JoinColumn(name = "product_inventory_id")
    private ProductInventory productInventory;

    @Column(name = "stock_keeping_unit")
    private String SKU;

    @Column(name = "price")
    private String price;

    @Column(name = "created_at")
    private LocalDateTime createdat;

    @Column(name = "modified_at")
    private LocalDateTime modifiedat;

    @Column(name = "deleted_at")
    private LocalDateTime deletedat;

    @Column(name = "image")
    private String image;
}
