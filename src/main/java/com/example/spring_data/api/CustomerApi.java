package com.example.spring_data.api;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.manager.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerApi {

    private CustomerManager customerManager;

    @Autowired
    public CustomerApi(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @GetMapping("/customer/all")
    public Iterable<Customer> getAll(){
        return customerManager.findAll();
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getById(@PathVariable long id){
        return customerManager.findById(id);
    }

    @PostMapping("/admin/customer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerManager.save(customer);
    }

    @PutMapping("/admin/customer/{id}")
    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable long id){
        return customerManager.save(newCustomer);
    }

    @PatchMapping("/admin/customer/{id}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable long id){
        return customerManager.update(newCustomer, id);
    }
}
