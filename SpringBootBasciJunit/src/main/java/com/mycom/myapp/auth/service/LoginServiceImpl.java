package com.mycom.myapp.auth.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public UserResultDto login(String email, String password) {

        UserResultDto userResultDto = new UserResultDto();

        log.debug("login() 시작");

        // #1. id가 아닌 email로 find -> 2개의 select
//        Optional<User> optionalUser = userRepository.findByEmail(email);

        // #2. id로 find(하드코딩으로 테스트) -> join으로 1개의 select
//        Optional<User> optionalUser = userRepository.findById(4L);

        // #3. jpql
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 비밀번호 동일하면
            if(user.getPassword().equals(password)) {
                // User -> UserDto
                // UserRole 도 함께 처리
                Map<Integer, String> roles = new HashMap<>();
                // 사용자가 가진 role들에 각각 put해줌
                user.getUserRoles().forEach(userRole -> roles.put(userRole.getId(), userRole.getName()));

                UserDto userDto = UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(roles)
                        .build();
                userResultDto.setUserDto(userDto);
                userResultDto.setResult("success");
            } else{
                userResultDto.setResult("fail"); // password 불일치
            }
        } else{
            userResultDto.setResult("not found"); // 회원 존재하지 않음.
        }
        log.debug("login() 종료");
        return userResultDto;
    }
}
