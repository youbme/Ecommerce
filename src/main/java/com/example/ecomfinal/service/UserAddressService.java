package com.example.ecomfinal.service;

import com.example.ecomfinal.model.User;
import com.example.ecomfinal.model.UserAddress;
import com.example.ecomfinal.repository.UserAddressRepository;
import com.example.ecomfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    public UserAddress saveUserAddress(Integer id, UserAddress userAddress) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        userAddress.setUserid(user);
        return userAddressRepository.save(userAddress);
    }

    public List<UserAddress> findall() {
        return userAddressRepository.findAll();
    }

    public UserAddress deleteuseraddress(Integer id) throws ChangeSetPersister.NotFoundException {
        UserAddress userAddress= userAddressRepository.findById(id)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
                userAddressRepository.deleteById(id);
                return userAddress;
    }
}
