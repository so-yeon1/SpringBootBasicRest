package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/", "/index.html").permitAll() // 모두 허락
                                    .requestMatchers("/customer/**").hasAnyRole("ADMIN", "CUSTOMER") // ADMIN 또는 CUSTOMER ROLE만 접근 가능
                                    .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN ROLE만 접근 가능
                                    .anyRequest().authenticated(); // 나머지는 모두 인증 요함

                        }
                )
                .formLogin(form ->
                            form.defaultSuccessUrl("/", true)
                                .permitAll())                                       // 로그인하면 / 로 이동.
                .logout(logout -> logout.permitAll()) //  /logout url로 요청하면 자동으로 Spring security가 session invalidate. permitAll은 다른거 따지지말고 바로 수행하라는거.

                .build();
    }

    @Bean
    PasswordEncoder get(){
        return new BCryptPasswordEncoder();
    }
}
