package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.Cart;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.security.UserSpringDetails;
import com.example.ecomfinal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addcart")
    public String addtocart( @RequestBody Cart cart){

//        Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
        UserSpringDetails user = (UserSpringDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        String name = user.getUsername();
        Product pid =cart.getProductid();
        Integer quantity = cart.getQuantity();
        cartService.saveCartwithprod(pid,quantity,name);
        return name;
    }
}