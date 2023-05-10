package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.OrderDetails;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;


    @PostMapping("/save")
    public OrderDetails saveorderDetails(@RequestBody OrderDetails orderDetails){
        OrderDetails orderDetails1 = orderDetailsService.save(orderDetails);
        return orderDetails1;
    }

    @PostMapping("save/{uid}/{pid}")
    public OrderDetails saveorderDetailswithfk(@PathVariable("uid")Integer uid,
                                           @PathVariable("pid") Integer pid,
                                           @RequestBody OrderDetails orderDetails) throws ChangeSetPersister.NotFoundException {
        OrderDetails orderDetails1 = orderDetailsService.savewithfk(orderDetails,uid,pid);
        return orderDetails1;
    }

    @GetMapping("/retrieve")
    public List<OrderDetails> getallorderDetails(){
        List<OrderDetails> products = orderDetailsService.findall();
        return  products;
    }

    @GetMapping("delete/{id}")
    public Integer deleteorderDetails(@PathVariable("id") Integer id){
        orderDetailsService.deleteid(id);
        return id;
    }
}
