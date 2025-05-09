package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers(
                                            "/",
                                            "/index.html",
                                            "/csrf-token",
                                            "/login"
                                            ).permitAll() // 모두 허락
                                    .requestMatchers("/customer/**").hasAnyRole("ADMIN", "CUSTOMER") // ADMIN 또는 CUSTOMER ROLE만 접근 가능
                                    .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN ROLE만 접근 가능
                                    .anyRequest().authenticated(); // 나머지는 모두 인증 요함

                        }
                )
                // 이곳에 CSRF 설정.
//                .csrf(csrf->csrf.disable()) // csrf 무시 설정.(비추)
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // 쿠키로 CSRF 토큰 내려줌
                .formLogin(form ->
                            form
                                // 사용자정의 로그인페이지를 사용하려면, 기본적으로 CSRF를 전송하도록 구현해야 한다.
                                // 만약 구현하지 않으면 CSRF 토큰이 없다는 오류 발생, 로그인처리 불가.
                                // CSRF 무시 설정도 가능(비추)
                                .loginPage("/login.html") // 로그인창
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll())                                       // 로그인하면 / 로 이동.
                .logout(logout -> logout.permitAll()) //  /logout url로 요청하면 자동으로 Spring security가 session invalidate. permitAll은 다른거 따지지말고 바로 수행하라는거.

                .build();
    }

    @Bean
    PasswordEncoder get(){
        return new BCryptPasswordEncoder();
    }
}
