package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.User;
import com.example.ecomfinal.model.UserAddress;
import com.example.ecomfinal.service.UserAddressService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useraddress")
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;


    @PostMapping("/save/{id}")
    public UserAddress save(@PathVariable("id") Integer id, @RequestBody UserAddress userAddress) throws ChangeSetPersister.NotFoundException {
        UserAddress useraddress = userAddressService.saveUserAddress(id, userAddress);
        return useraddress;
    }

    @GetMapping("/retrieve")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public List<UserAddress> getalluseraddress(){
        List<UserAddress> userAddresses = userAddressService.findall();
        return userAddresses;
    }

    @GetMapping("delete/{id}")
    public UserAddress deleteuseraddress(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
       UserAddress userAddress = userAddressService.deleteuseraddress(id);
        return userAddress;
    }
}
