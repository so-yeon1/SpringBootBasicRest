package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

// Register 단계
// UserRepository - save
// UserRoleRepository - find, save
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    @Transactional
    public UserResultDto insertUser(User user) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            // #1. 기존 UserRole을 find 후 영속화. name = ROLE_CUSTOMER
//        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER"); // name = ROLE_CUSTOMER인 role find
//        List<UserRole> userRoles = List.of(userRole);
//        user.setUserRoles(userRoles); // user에 role(=role_customer) 지정
//        User savedUser = userRepository.save(user); // user insert (가입)

            // #2. 새로운 UserRole 생성.

            // #2-1. userRole 객체를 save X => 영속화 X. 오류
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            List<UserRole> userRoles = List.of(userRole);
//            user.setUserRoles(userRoles);
//            User savedUser = userRepository.save(user);


            // #2-2. userRole 객체 save O => 영속화 O
        UserRole userRole = new UserRole();
        userRole.setName("role_test");
        List<UserRole> userRoles = List.of(userRole);
        user.setUserRoles(userRoles);
        userRoleRepository.save(userRole); // 위와 다른 부분. (userRole save O)
        User savedUser = userRepository.save(user);
        userRepository.flush();
        userRoleRepository.flush();

            // #3. transaction + #1번 상황
//        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER"); // name = ROLE_CUSTOMER인 role find
//        List<UserRole> userRoles = List.of(userRole);
//        user.setUserRoles(userRoles); // user에 role(=role_customer) 지정
//        User savedUser = userRepository.save(user); // user insert (가입)
//
//        // 예외발생. transaction 처리해서 rollback됨.
//        String s = null;
//        s.length();
//
//        System.out.println(savedUser);


            // #4. transaction + #2-2번 상황
//        UserRole userRole = new UserRole();
//        userRole.setName("role_test");
//        List<UserRole> userRoles = List.of(userRole);
//        user.setUserRoles(userRoles);
//        userRoleRepository.save(userRole); // 위와 다른 부분. (userRole save O)
//
//        // 예외발생. transaction 처리해서 rollback됨.
//        String s = null;
//        s.length();
//
//        User savedUser = userRepository.save(user);


            // #5. #2-1번의 오류 발생을 연관관계(OneToMany)의 persist 처리. User에서 cascade = persist 설정
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            List<UserRole> userRoles = List.of(userRole);
//            user.setUserRoles(userRoles);
//            User savedUser = userRepository.save(user);

            // #6.
            // @Transactional 상황에서는 TransactionAspect 가 관여하고, Proxy 객체를 통해 우리의 코드를
            // 대신 호출하고 최종적인 예외가 발생하지 않으면 TransactionAspect 가 commit() 을 수행하는데
            // 이 과정에서 예외가 발생되므로 우리 코드 밖에서 생기는 예외를 우리 코드에서 catch 하지 못하는 상황
            // @Transactional은 예외가 발생해야 rollback을 자동으로 해 줌.
            // => 지금 catch에서 예외를 잡아버려서 rollback이 안되니까 catch문에서 명시적으로 rollback해주는거임
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            List<UserRole> userRoles = List.of(userRole);
//            user.setUserRoles(userRoles);
//            User savedUser = userRepository.save(user);
//            userRepository.flush();

            userResultDto.setResult("success");
        }catch (Exception e){
            // try-catch문에서 rollback시키는거
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }
}
