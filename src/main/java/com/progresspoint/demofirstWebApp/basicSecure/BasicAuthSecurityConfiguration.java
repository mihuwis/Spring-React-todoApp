package com.progresspoint.demofirstWebApp.basicSecure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// THIS WAS FOR BASIC CONFIG only
//@Configuration
public class BasicAuthSecurityConfiguration {

    // When return just httpSecurity.build(); then there are NO security over API
//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.authorizeHttpRequests((auth) -> auth
                                .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.OPTIONS, "/**")).permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .build();

    }
}
