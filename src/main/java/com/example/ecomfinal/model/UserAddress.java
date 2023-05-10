package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User userid;
    private String address;
    private String city;
    private int postal_code;
    private String country;
    private int telephone;
    private int mobile;

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", userid=" + userid +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postal_code=" + postal_code +
                ", country='" + country + '\'' +
                ", telephone=" + telephone +
                ", mobile=" + mobile +
                '}';
    }
}
