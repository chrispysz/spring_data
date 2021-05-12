package com.example.spring_data.user;

import com.example.spring_data.PasswordEncoderConfig;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoBuilder {

    public UserDto toDto(User user) {
        PasswordEncoder encoder = new PasswordEncoderConfig().passwordEncoder();
        UserDto dto = new UserDto(user.getName(), encoder.encode(user.getPassword()), user.getRole());
        return dto;
    }


}
