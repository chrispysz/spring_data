package com.example.spring_data.api;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.dao.order.Order;
import com.example.spring_data.manager.CustomerManager;
import com.example.spring_data.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderApi {
    private OrderManager orderManager;

    @Autowired
    public OrderApi(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @GetMapping("/order/all")
    public Iterable<Order> getAll(){
        return orderManager.findAll();
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getById(@PathVariable long id){
        return orderManager.findById(id);
    }

    @PostMapping("/admin/order")
    public Order addOrder(@RequestBody Order order){
        return orderManager.save(order);
    }

    @PutMapping("/admin/order/{id}")
    public Order replaceOrder(@RequestBody Order newOrder, @PathVariable long id){
        return orderManager.save(newOrder);
    }

    @PatchMapping("/admin/order/{id}")
    public Order updateOrder(@RequestBody Order newOrder, @PathVariable long id){
        return orderManager.update(newOrder, id);
    }
}

