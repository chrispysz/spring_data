package com.example.spring_data.manager;

import com.example.spring_data.dao.order.Order;
import com.example.spring_data.dao.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderManager {
    private OrderRepository orderRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }


    public Order update(Order newOrder, long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.map(o -> {
            o.setId(id);
            o.setCustomer(newOrder.getCustomer());
            o.setPlaceDate(newOrder.getPlaceDate());
            o.setProducts(newOrder.getProducts());
            o.setStatus(newOrder.getStatus());
            return orderRepository.save(o);
        });
        return order.get();
    }

}
