package com.example.spring_data.manager;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.dao.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerManager {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer update(Customer newCustomer, long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        customer.map(c -> {
            c.setId(id);
            c.setName(newCustomer.getName());
            c.setAddress(newCustomer.getAddress());
            return customerRepository.save(c);
        });
        return customer.get();
    }

}
