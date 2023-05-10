package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.ProductCategory;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.service.ProductCategoryService;
import com.example.ecomfinal.service.ProductService;
import com.example.ecomfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping("/save")
    public ResponseEntity<ProductCategory> saveUser(@RequestBody ProductCategory productCategory){

        ResponseEntity<ProductCategory> response = new ResponseEntity<>(productCategoryService.saveProductCategory(productCategory), HttpStatus.OK);
        return response;
    }

    @GetMapping("/retrieve")
    public List<ProductCategory> getallUser(){
        List<ProductCategory> productCategory = productCategoryService.getall();
        return productCategory;
    }
    @GetMapping("/delete/{id}")
    public Integer deleteUser(@PathVariable("id") Integer id){
        productCategoryService.delete(id);
        return id;
    }
}
