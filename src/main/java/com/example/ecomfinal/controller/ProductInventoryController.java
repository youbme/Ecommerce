package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.ProductInventory;
import com.example.ecomfinal.repository.ProductInventoryRepository;
import com.example.ecomfinal.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/productinventory")
public class ProductInventoryController {

    @Autowired
    ProductInventoryService productInventoryService;

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    @PostMapping("/save")
    public ProductInventory saveInventory(@RequestBody ProductInventory productInventory){
        return productInventoryRepository.save(productInventory);
    }

    @GetMapping("/retrieve")
    public List<ProductInventory> getallprodInventory(){
        return  productInventoryRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteprodinventory(@RequestParam ("id") Integer id){
                productInventoryRepository.deleteById(id);
                return id;
    }
}
