package com.mycom.myapp.webapp.springboottest;

import com.mycom.myapp.auth.controller.LoginController;
import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class LoginTest {

//    @Autowired
//    private MockMvc mockMvc;

    // Controller
    @Autowired
    private LoginController loginController;

    @Autowired
    private HttpSession httpSession;

    @Test
    public void testLogin() {
        UserResultDto userResultDto = loginController
                .login("user1@mycom.com", "password1", httpSession);
        assertEquals("success", userResultDto.getResult());
    }


    // Service
    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin2() {
        UserResultDto userResultDto = loginService.login("user2@mycom.com", "password2");
        assertEquals("success", userResultDto.getResult());
        assertNotNull(userResultDto.getUserDto()); // 생성한 userDto가 존재하는지
    }


    // Repository
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLogin3() {
        Optional<User> optinalUser = userRepository.findByEmail("user1@mycom.com");
        assertNotNull(optinalUser.get());
    }
}
