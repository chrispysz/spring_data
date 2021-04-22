package com.example.spring_data.manager;

import com.example.spring_data.dao.product.Product;
import com.example.spring_data.dao.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductManager {
    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public Product update(Product newProduct, long id) {
        Optional<Product> product = productRepository.findById(id);
        product.map(p -> {
            p.setId(id);
            p.setName(newProduct.getName());
            p.setAvailable(newProduct.isAvailable());
            p.setPrice(newProduct.getPrice());
            return productRepository.save(p);
        });
        return product.get();
    }
}
