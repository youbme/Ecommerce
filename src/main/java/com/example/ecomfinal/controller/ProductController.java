package com.example.ecomfinal.controller;

import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product, @RequestParam("file")MultipartFile file){
        Product product1 = productService.save(product, file);
        return product1;
    }

    @PostMapping("save/{id}")
    public Product saveProductwithCategory(@PathVariable("id")Integer id,
                                           @RequestBody Product product) throws ChangeSetPersister.NotFoundException {
        Product product1 = productService.savewithcat(product,id);
        return product1;
    }

    @PostMapping("/savewithcatinv")
    public Product saveProductwthCatInv(@RequestParam("cid") Integer cid, @RequestParam("iid") Integer iid,
                                        @RequestBody Product product){
        Product product1 = productService.savewithcatinv(product, cid, iid);
        return product1;
    }


    @GetMapping("/retrieve")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public List<Product> getallProduct(){
        List<Product> products = productService.findall();
        return  products;
    }

    @GetMapping("/delete")
    public Integer deleteproduct(@RequestParam("id") Integer id){
        productService.deleteid(id);
        return id;
    }
}
