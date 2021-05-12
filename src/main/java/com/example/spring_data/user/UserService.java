package com.example.spring_data.user;

import com.example.spring_data.dao.customer.Customer;
import com.example.spring_data.dao.customer.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class UserService {

    private UserDtoRepository userDtoRepository;

    @Autowired
    public UserService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }


    @PostMapping("/api/getToken")
    public String provideToken(@RequestBody User user) {
        Iterable<UserDto> users = userDtoRepository.findAll();

        for (UserDto u : users) {
            if (u.getName().equals(user.getName()) && BCrypt.checkpw(user.getPassword(), u.getPasswordHash())) {
                long currentTimeMillis = System.currentTimeMillis();

                return Jwts.builder()
                        .setSubject(user.getName())
                        .claim("role", u.getRole())
                        .setIssuedAt(new Date(currentTimeMillis))
                        .setExpiration(new Date(currentTimeMillis + 20000))
                        .signWith(SignatureAlgorithm.HS512, "HU7cB55PzI")
                        .compact();
            }
        }

        return "401";


    }
}
