package com.example.spring_data.api;

import com.example.spring_data.dao.order.Order;
import com.example.spring_data.dao.product.Product;
import com.example.spring_data.manager.OrderManager;
import com.example.spring_data.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductApi {
    private ProductManager productManager;

    @Autowired
    public ProductApi(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping("/product/all")
    public Iterable<Product> getAll(){
        return productManager.findAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getById(@PathVariable long id){
        return productManager.findById(id);
    }

    @PostMapping("/admin/product")
    public Product addProduct(@RequestBody Product product){
        return productManager.save(product);
    }

    @PutMapping("/admin/product/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable long id){
        return productManager.save(newProduct);
    }

    @PatchMapping("/admin/product/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable long id){
        return productManager.update(newProduct, id);
    }
}
