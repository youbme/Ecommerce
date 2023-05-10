package com.example.ecomfinal.service;

import com.example.ecomfinal.model.OrderDetails;
import com.example.ecomfinal.model.OrderItems;
import com.example.ecomfinal.model.Product;
import com.example.ecomfinal.model.ProductCategory;
import com.example.ecomfinal.repository.OrderDetailsRepository;
import com.example.ecomfinal.repository.OrderItemsRepository;
import com.example.ecomfinal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    public OrderItems save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }

    public List<OrderItems> findall() {
        List<OrderItems> orderItems = orderItemsRepository.findAll();
        return  orderItems;
    }

    public void deleteid(Integer id) {
        orderItemsRepository.deleteById(id);
    }

    public OrderItems savewithfk(OrderItems orderItems, Integer oid, Integer pid) throws ChangeSetPersister.NotFoundException {
        Product product = productRepository.findById(pid)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        OrderDetails orderDetail = orderDetailsRepository.findById(oid)
                .orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        orderItems.setProduct(product);
        orderItems.setOrderDetails(orderDetail);
        return orderItemsRepository.save(orderItems);
    }
}
