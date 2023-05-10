package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double total;

    @OneToOne(mappedBy = "orderDetails", cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

    private LocalDateTime createat;
    private LocalDateTime modifiedat;

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
}
