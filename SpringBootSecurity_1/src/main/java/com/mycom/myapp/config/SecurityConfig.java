package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // http security 정책을 모든 요청에 대해 전부 허락
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests( request -> request.anyRequest().permitAll()) // 모든 request
//                .build();
//    }
//     authorizeHttpRequests 여기서 authorize: ahtentication 포함.
//     권한관리를 어떻게 해야돼? 하고 requst 던져줬더니, request에 대해 모든 권한 허락.
//     기본적으로 보안처리를 filter 기반으로.
//     시큐리티 사용으로 인터셉터의 역할이 줄어들게 됨


    // 모든 요청에 대해 전부 인증을 받아야 함.
    // 로그인 페이지를 통한 인증
    // 아래가 아무런 설정 없는 경우와 동일한 동작임.
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests( request -> request.anyRequest().authenticated()) // 모든 요청에 대해 전부 인증을 받아야 함.
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }


    // 모든 요청에 대해 전부 인증을 받아야 함.
    // 단, /, /index.html 에 대해서는 모두 허락.
    // 로그인 페이지를 통한 인증
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/", "/index.html").permitAll(); // 모두 허락
                            request.anyRequest().authenticated(); // 나머지는 모두 인증 요함

                        }
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
