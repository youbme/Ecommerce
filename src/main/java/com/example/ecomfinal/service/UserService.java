package com.example.ecomfinal.service;

import com.example.ecomfinal.exception.UserNotFoundException;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User saveUser( User user){

      return  userRepository.save(user);
    }

    public List<User> getallUsers() {

        return  userRepository.findAll();
    }

    public void deleteuser(Integer id) {
        userRepository.deleteById(id);
    }

    public User updatebyid(User newUser, int id) {

//        User user = userRepository.findById(id)
//                .orElseThrow(()-> new UserNotFoundException(id));
//
//        user.setUsername(newUser.getUsername());
//        user.setFirstName(newUser.getFirstName());
//        user.setLastName(newUser.getLastName());
//        user.setTelephone(newUser.getTelephone());
//        user.setPassword(newUser.getPassword());
//    return user;

        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setTelephone(newUser.getTelephone());
        user.setPassword(newUser.getPassword());

            return  userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }
}
