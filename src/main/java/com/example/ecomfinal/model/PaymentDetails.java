package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_details_id", referencedColumnName = "id")
    private OrderDetails orderDetails;
    private int amount;
    private String provider;
    private String stats;
    private LocalDateTime createdat;
    private LocalDateTime modifiedat;

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

}
