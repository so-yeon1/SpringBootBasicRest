package com.mycom.myapp.webapp.springboottest;

import com.mycom.myapp.auth.controller.LoginController;
import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.controller.UserController;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class RegisterTest {

//    @Autowired
//    private MockMvc mockMvc;

    // 개발 순서
    // 초기 설계를 마무리한 상태에서 실제 코딩 순서
    // repository -> service -> controller

    // userRepository test는 user Entity 만 save
    // Hibernate: insert into user (email,name,password) values (?,?,?)
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional // 자동 rollback
    public void testRegister(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        User saveUser = userRepository.save(user);
        assertNotNull(saveUser);
    }

    // userService test는 UserRole Entity, User Entity 모두 save
    // Hibernate: insert into user_role (name) values (?)
    // Hibernate: insert into user (email,name,password) values (?,?,?)
    // Hibernate: insert into user_user_roles (user_id,user_roles_id) values (?,?)
    @Autowired
    private UserService userService;

    @Test
    @Transactional // 자동 rollback
    public void testRegister2(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        UserResultDto userResultDto = userService.insertUser(user);
        assertEquals("success", userResultDto.getResult());
    }



    // Controller에서 service호출하므로 service와 동일한 insert문 수행.
    @Autowired
    private UserController userController;

    @Test
    @Transactional // 자동 rollback
    public void testRegister3(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        UserResultDto userResultDto = userController.insertUser(user);
        assertEquals("success", userResultDto.getResult());
    }


    // 이게뭔데
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void testRegister4() throws Exception {
        this.mockMvc.perform(
                post("/users/register")
                        .param("name","홍길동")
                        .param("email","hong@gildong.com")
                        .param("password","1234")
                )
                .andExpect(status().isOk());
    }
}
