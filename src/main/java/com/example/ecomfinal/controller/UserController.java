package com.example.ecomfinal.controller;

import com.example.ecomfinal.exception.UserNotFoundException;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.UserRepository;
import com.example.ecomfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/saveUser")
        public ResponseEntity<User> saveUser(@RequestBody User user){

        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setTelephone(user.getTelephone());
        user1.setRole(user.getRole());

        ResponseEntity<User> response = new ResponseEntity<>(userService.saveUser(user1),HttpStatus.OK);
        return response;
        }

    @GetMapping("/retrieve")
        public List<User> getallUser(){
        List<User> users = userService.getallUsers();
        return users;
    }

    @DeleteMapping("/deleteUser/{id}")
    public Integer deleteUser(@PathVariable("id") Integer id){
        userService.deleteuser(id);
        return id;
    }

    @GetMapping("/retrieve/{id}")
    public User getUserById(@PathVariable int id){
      User user=  userRepository.findById(id)
              .orElseThrow(()-> new UserNotFoundException(id));
      return user;
    }
    @PutMapping("/edit/{id}")
    public User updateuser(@RequestBody User newUser, @PathVariable int id){
        User user = userService.updatebyid(newUser, id);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteuser(@PathVariable int id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User wit id"+id+" has been deleted success.";
    }



}

