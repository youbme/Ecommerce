package com.example.ecomfinal.service;


import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.model.ProductCategory;
import com.example.ecomfinal.model.User;
import com.example.ecomfinal.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategory saveProductCategory(ProductCategory productCategory){

        return  productCategoryRepository.save(productCategory);
    }

    public List<ProductCategory> getall() {

        return  productCategoryRepository.findAll();
    }

    public void delete(Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
