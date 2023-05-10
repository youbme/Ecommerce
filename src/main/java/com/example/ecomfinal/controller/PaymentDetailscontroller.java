package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.PaymentDetails;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDetails")
public class PaymentDetailscontroller {

    @Autowired
    PaymentDetailsService paymentDetailsService;

    @PostMapping("/save")
    public PaymentDetails savepayment(@RequestBody PaymentDetails paymentDetails){
        PaymentDetails paymentDetailss = paymentDetailsService.save(paymentDetails);
        return paymentDetailss;
    }


    @GetMapping("/retrieve")
    public List<PaymentDetails> getallpayment(){
        List<PaymentDetails> paymentDetails = paymentDetailsService.findall();
        return  paymentDetails;
    }

    @GetMapping("delete/{id}")
    public Integer deletepayment(@PathVariable("id") Integer id){
        paymentDetailsService.deleteid(id);
        return id;
    }
}
