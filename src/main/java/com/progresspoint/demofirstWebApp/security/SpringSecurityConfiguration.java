package com.progresspoint.demofirstWebApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetails(){
        UserDetails userDetails1 = setUserDetails("Mihu", "mmm");
        UserDetails userDetails2 = setUserDetails("Ada", "aaa");
        UserDetails userDetails3 = setUserDetails("Pablo", "ppp");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    private UserDetails setUserDetails(String userName, String password) {
        Function<String, String> encodedPass = input -> passwordEncoder().encode(input);
        return User.builder()
                .passwordEncoder(encodedPass)
                .username(userName)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Configure this to let h2 console get inn
    // CSRF disable and Frames allowed
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }
}
