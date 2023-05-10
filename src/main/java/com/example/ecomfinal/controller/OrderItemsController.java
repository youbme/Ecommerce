package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.OrderItems;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    OrderItemsService orderItemsService;

    @PostMapping("/save")
    public OrderItems saveProduct(@RequestBody OrderItems orderItems){
        OrderItems orderItems1 = orderItemsService.save(orderItems);
        return orderItems1;
    }

    @PostMapping("save/{oid},{pid}")
    public OrderItems saveProductwithCategory(@PathVariable("oid")Integer oid,
                                           @PathVariable("pid") Integer pid,
                                           @RequestBody OrderItems orderItems) throws ChangeSetPersister.NotFoundException {
        OrderItems orderItems1 = orderItemsService.savewithfk(orderItems,oid,pid);
        return orderItems1;
    }

    @GetMapping("/retrieve")
    public List<OrderItems> getallProduct(){
        List<OrderItems> orderItems = orderItemsService.findall();
        return  orderItems;
    }

    @GetMapping("delete/{id}")
    public Integer deleteproduct(@PathVariable("id") Integer id){
        orderItemsService.deleteid(id);
        return id;
    }
}
