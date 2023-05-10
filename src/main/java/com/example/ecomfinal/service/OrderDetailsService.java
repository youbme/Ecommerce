package com.example.ecomfinal.service;

import com.example.ecomfinal.model.*;
import com.example.ecomfinal.repository.OrderDetailsRepository;
import com.example.ecomfinal.repository.PaymentDetailsRepository;
import com.example.ecomfinal.repository.ProductRepository;
import com.example.ecomfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    UserRepository userRepository;

     @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    public OrderDetails save(OrderDetails orderDetails) {
        return  orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> findall() {
        List<OrderDetails> orderItems = orderDetailsRepository.findAll();
        return orderItems;
    }

    public void deleteid(Integer id) {

        orderDetailsRepository.deleteById(id);
    }

    public OrderDetails savewithfk(OrderDetails orderDetails, Integer uid, Integer pid) throws ChangeSetPersister.NotFoundException {

        User user = userRepository.findById(uid)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        PaymentDetails paymentDetails= paymentDetailsRepository.findById(pid)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        orderDetails.setUser(user);
        orderDetails.setPaymentDetails(paymentDetails);
        return orderDetailsRepository.save(orderDetails);
    }
}
