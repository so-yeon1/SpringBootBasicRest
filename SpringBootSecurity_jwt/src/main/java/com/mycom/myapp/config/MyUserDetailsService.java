package com.mycom.myapp.config;

import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // role 고려 o
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // PasswordEncoder 객체 구현 클래스
//        System.out.println(passwordEncoder.getClass());
//
//
//
//        // 하드코딩(dskim/1234)
//        if("dskim".equals(username)) {
//            return User.builder()
//                    .username("dskim")
//                    .password(passwordEncoder.encode("1234"))
//                    .roles("ADMIN")
//                    .build();
//        } else{
//            throw new UsernameNotFoundException("user not found");
//        }
//    }

//    private final UserRepository userRepository;
//
//    // dskim/1234 => User Entity 를 이용한 UserDetails
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//
//
//        if( optionalUser.isPresent() ) {
//            User user = optionalUser.get();
//            List<UserRole> listUserRole = user.getUserRoles();
//            String[] rolesStrArray = listUserRole.stream().map( UserRole::getName ).toArray( String[]::new );
//            // user 와 listUserRole 을 이용해서 userDetails 객체 생성, return
//            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getEmail())
//                    .password(user.getPassword())
//                    .roles(rolesStrArray)
//                    .build();
//
//            return userDetails;
//        }
//
//        throw new UsernameNotFoundException("User not found");
//    }



        private final UserRepository userRepository;

    // dskim/1234 => User Entity 를 이용한 UserDetails
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);


        if( optionalUser.isPresent() ) {
            User user = optionalUser.get();
            List<UserRole> listUserRole = user.getUserRoles();

            List<SimpleGrantedAuthority> authorities =
                    listUserRole.stream().map(UserRole::getName).map(SimpleGrantedAuthority::new).toList();
// role_ 들어가야 함.
            //            List<SimpleGrantedAuthority> authorites =
            //                    listUserRole.stream().map(UserRole::getName).map( name -> "ROLE_" + name).map(SimpleGrantedAuthority::new).toList();

            // MyUserDetails 사용(기존 UserDetails를 사용자 정의 코드로 커스터마이징)
            UserDetails userDetails = MyUserDetails.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .email(user.getEmail()) // user의 다양한 정보를 추가.
                    .authorities(authorities)
                    .build();

            return userDetails;
        }

        throw new UsernameNotFoundException("User not found");
    }
}


// ---------------------------------------------------------강사님코드


//package com.mycom.myapp.config;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import lombok.RequiredArgsConstructor;
//@Service
//@RequiredArgsConstructor
//public class MyUserDetailsService implements UserDetailsService{
//    // DI
//    private final PasswordEncoder passwordEncoder;
//
//    // 기본 form ui 로그인 그대로 ( 우리가 별도의 login 페이지를 제공 X )
//    // username, password 가 user / console password 사용 X <= UserDetailsService 를 제공하므로
//    // form ui 에 사용자가 입력한 username 값이 loadUserByUsername() 파라미터로 전달 (username)
//    // DB 를 통해서 ( JPA 경우 UserRepository 를 거쳐서 ) username 으로 select username, password 를 가져와서
//    // UserDetails 구현 객체를 만들어서 return 해 줘야 한다.
//    // UserDetails 구현 객체는 우선 Spring security 에서 제공하는 org.springframework.security.core.userdetails.User 를 사용
//
//    // ROLE 고려 X
////  @Override
////  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////      // PasswordEncoder 객체 구현 클래스
////      System.out.println(passwordEncoder.getClass());
////
////      if( "dskim".equals(username) ) {
////          return User.builder()
////                  .username("dskim")
////                  .password(passwordEncoder.encode("1234"))
////                  .build();
////      }else {
////          throw new UsernameNotFoundException("User not found");
////      }
////  }
//
//    // ROLE 고려 O
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // PasswordEncoder 객체 구현 클래스
//        System.out.println(passwordEncoder.getClass());
//
//        if( "dskim".equals(username) ) {
//            return User.builder()
//                    .username("dskim")
//                    .password(passwordEncoder.encode("1234"))
//                    .roles("CUSTOMER")
//                    .build();
//        }else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
//}
