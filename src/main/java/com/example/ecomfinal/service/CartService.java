package com.example.ecomfinal.service;

import com.example.ecomfinal.exception.UserNotFoundException;
import com.example.ecomfinal.model.Cart;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.CartRepository;
import com.example.ecomfinal.repository.ProductRepository;
import com.example.ecomfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public Cart savecart(Cart cart){

        Cart cart1 =cartRepository.save(cart);
        return cart1;
    }


    public Cart saveCartwithprod( Product pid, Integer quantity, String name){

        Integer productid = pid.getId();

        Product product = productRepository.findById(productid)
                .orElseThrow(()-> new UserNotFoundException(productid));
        User user = userRepository.findByName(name)
                .orElseThrow(()-> new UserNotFoundException(name));
        Cart cart = new Cart();
        cart.setUserid(user);
        cart.setProductid(product);
        cart.setQuantity(quantity);
        cart.setDeleted(false);
        Cart cart1 = cartRepository.save(cart);
        return cart1;

    }

}
