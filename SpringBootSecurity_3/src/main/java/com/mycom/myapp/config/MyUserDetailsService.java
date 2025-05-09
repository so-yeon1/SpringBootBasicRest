package com.mycom.myapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    // DI
    // 암호화 복호화하는거
    private final PasswordEncoder passwordEncoder;

    // 기본 form ui 로그인 그대로.
    // UserDetailsService를 제공하므로 => Username:user, password: 콘솔복붙은 사용 X.
    // 폼 ui에 사용자가 입력한 username값이 loadUserByUsername()의 파라미터로 전달됨.
    // DB를 통해서 (jpa 경우 userRepository 를 거쳐서) username 으로 select username => password를 가져와서
    // UserDetails 구현 객체를 만들어서 return 해줘야 한다.
    // UserDetails 구현 객체는 우선 Spring security에서 제공하는 org.springframework.security.core.userdetails.User 를 사용

    // role 고려 X
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // PasswordEncoder 객체 구현 클래스
//        System.out.println(passwordEncoder.getClass());
//
//        // 하드코딩(dskim/1234)
//        if("dskim".equals(username)) {
//            return User.builder()
//                .username("dskim")
//                .password(passwordEncoder.encode("1234"))
//                .build();
//        } else{
//            throw new UsernameNotFoundException("user not found");
//        }
//    }

    // role 고려 oS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // PasswordEncoder 객체 구현 클래스
        System.out.println(passwordEncoder.getClass());

        // 하드코딩(dskim/1234)
        if("dskim".equals(username)) {
            return User.builder()
                    .username("dskim")
                    .password(passwordEncoder.encode("1234"))
                    .roles("ADMIN")
                    .build();
        } else{
            throw new UsernameNotFoundException("user not found");
        }
    }
}

