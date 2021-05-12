package com.example.spring_data.user;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.dao.customer.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class UserService {


    @PostMapping("/api/getToken")
    public String provideToken(@RequestBody User user){

        long currentTimeMillis= System.currentTimeMillis();

        return Jwts.builder().setSubject(user.getName())
                .claim("roles","user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis+20000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();

    }
}
