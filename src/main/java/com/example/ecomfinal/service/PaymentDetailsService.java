package com.example.ecomfinal.service;

import com.example.ecomfinal.model.PaymentDetails;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.repository.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailsService {

    @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    public PaymentDetails save(PaymentDetails paymentDetails) {

        return paymentDetailsRepository.save(paymentDetails);
    }

    public List<PaymentDetails> findall() {
        List<PaymentDetails> paymentDetails = paymentDetailsRepository.findAll();
        return paymentDetails;
    }

    public void deleteid(Integer id) {
        paymentDetailsRepository.deleteById(id);
    }
}
