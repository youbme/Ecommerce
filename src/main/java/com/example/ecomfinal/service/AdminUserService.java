package com.example.ecomfinal.service;

import com.example.ecomfinal.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    @Autowired
    AdminUserRepository adminUserRepository;
}
