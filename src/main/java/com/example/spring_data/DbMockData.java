package com.example.spring_data;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.dao.customer.CustomerRepository;
import com.example.spring_data.dao.order.Order;
import com.example.spring_data.dao.order.OrderRepository;
import com.example.spring_data.dao.product.Product;
import com.example.spring_data.dao.product.ProductRepository;
import com.example.spring_data.user.User;
import com.example.spring_data.user.UserDtoBuilder;
import com.example.spring_data.user.UserDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private UserDtoRepository userDtoRepository;

    @Autowired
    public DbMockData(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, UserDtoRepository userDtoRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.userDtoRepository = userDtoRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {
        Product product = new Product("Korek", 2.55f, true);
        Product product1 = new Product("Rura", 5f, true);
        Customer customer = new Customer("Jak Kowalski", "Wrocław");
        Set<Product> products = new HashSet<>() {
            {
                add(product);
                add(product1);
            }
        };
        Order order = new Order(customer, products, LocalDateTime.now(), "in progress");

        productRepository.save(product);
        productRepository.save(product1);
        customerRepository.save(customer);
        orderRepository.save(order);

        User user1=new User("John Doe", "pass1", "ROLE_CUSTOMER");
        User user2=new User("Mike Wazowski", "pass2", "ROLE_ADMIN");
        UserDtoBuilder userDtoBuilder = new UserDtoBuilder();


        userDtoRepository.save(userDtoBuilder.toDto(user1));
        userDtoRepository.save(userDtoBuilder.toDto(user2));
    }
}
