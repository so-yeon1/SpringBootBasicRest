package com.mycom.myapp.config;

import com.mycom.myapp.jwt.JwtAuthenticationFilter;
import com.mycom.myapp.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    PasswordEncoder get(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    MyAuthenticationEntryPoint entryPoint() {return new MyAuthenticationEntryPoint();}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    MyAuthenticationEntryPoint entryPoint) throws Exception {
        return http
                // form login 관련 disable
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers(
                                            "/",
                                            "/index.html",
                                            "/login",
                                            "/login.html",
                                            "/register.html",
                                            "/register",
                                            "/users/**"
                                            ).permitAll() // 모두 허락
                                    .requestMatchers("/customer/**").hasAnyRole("ADMIN", "CUSTOMER") // ADMIN 또는 CUSTOMER ROLE만 접근 가능
                                    .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN ROLE만 접근 가능
                                    .anyRequest().authenticated(); // 나머지는 모두 인증 요함

                        }
                )
                // formLogin 방식에서는 허락되지 않는 요청에 대해 자동으로 login.html 페이지로 분기
                // formLogin을 사용 X => 예외발생 => json 응답해야 함. (login 필요)
                .exceptionHandling(
                        exceptionHandlingCustomizer ->
                                exceptionHandlingCustomizer.authenticationEntryPoint(entryPoint))

                // formLogin 방식에서는 Spring Security가 자동으로 filter 처리 (UsernamePasswordAuthenticationFilter)
                // formLogin을 사용 X => 위 필터 앞에서 한 번만 수행되는 JWT 인증 필터를 적용.
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

                .build();
    }

}
