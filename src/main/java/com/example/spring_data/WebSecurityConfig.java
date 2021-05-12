package com.example.spring_data;

import com.example.spring_data.user.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/api/getToken"
        );
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/").permitAll()
                .and().authorizeRequests().antMatchers("/api/customer/**").hasRole("CUSTOMER")
                .and().authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN")
                .and().addFilter(new JwtFilter(authenticationManager()));


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
