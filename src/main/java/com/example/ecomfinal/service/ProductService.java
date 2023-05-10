package com.example.ecomfinal.service;

import com.example.ecomfinal.exception.UserNotFoundException;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.model.ProductCategory;
import com.example.ecomfinal.model.ProductInventory;
import com.example.ecomfinal.repository.ProductCategoryRepository;
import com.example.ecomfinal.repository.ProductInventoryRepository;
import com.example.ecomfinal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    public Product save(Product product, MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }

        try{
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productRepository.save(product);
    }

    public List<Product> findall() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public void deleteid(Integer id) {
        productRepository.deleteById(id);
    }

    public Product savewithcat(Product product, Integer id) throws ChangeSetPersister.NotFoundException {
        ProductCategory prodcat = productCategoryRepository.findById(id)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        product.setCategoryid(prodcat);
        Product product1 = productRepository.save(product);
        return product1;
    }

    public Product savewithcatinv(Product product, Integer cid, Integer iid) {

        ProductCategory prodcat = productCategoryRepository.findById(cid)
                .orElseThrow(()-> new UserNotFoundException(cid));
        ProductInventory prodinv = productInventoryRepository.findById(iid)
                .orElseThrow(()-> new UserNotFoundException(iid));
        product.setCategoryid(prodcat);
        product.setProductInventory(prodinv);
        Product product1 = productRepository.save(product);
        return product1;
    }
}
