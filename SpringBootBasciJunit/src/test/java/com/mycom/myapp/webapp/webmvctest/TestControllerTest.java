package com.mycom.myapp.webapp.webmvctest;

import com.mycom.myapp.user.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // #1.
    // GET
    @Test
    @Order(1)
    public void testHello() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect( status().isOk()); // 응답 200이 떨어지는 지 보고싶을 때
    }

    // #2.
    // POST
    @Test
    @Order(2)
    public void testParam1() throws Exception {
        this.mockMvc.perform(
                    post("/param1")
                            .param("id", "111")
                            .param("name","홍길동")
                )
                .andExpect( status().isOk() ); // 응답 200이 떨어지는 지 보고싶을 때.
                                                // 잘 전송되고, 응답 잘 받는지.
    }

    // #3.
    @Test
    @Order(3)
    public void testParam2() throws Exception {
        this.mockMvc.perform(
                    post("/param2")
                            .param("id", "111")
                            .param("name","홍길동")
                )
                .andExpect( status().isOk() ); // 응답 200이 떨어지는 지 보고싶을 때.
    }
    // 빠르게 컨트롤러 만들고 테스트하고 할 때는 무거운 spring~~ 보다는 webmvctest가 나음.


    // #4.
    @Test
    @Order(4)
    public void testResponse1() throws Exception {
        this.mockMvc.perform(
                        post("/response1")
                            .param("id", "111")
                            .param("name","홍길동")
                )
                .andExpect( status().isOk() )
                .andExpect(content().string("success")); // "success"값을 받고있는지 확인
    }


    // #5.
    @Test
    @Order(5)
    public void testResponse2() throws Exception {
        this.mockMvc.perform(
                    post("/response2")
                            .param("id", "111")
                            .param("name","홍길동")
                )
                .andExpect( status().isOk() ) // 정상적으로 수행되는지 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // 응답으로 받은게 JSON인지 확인
    }


    // #6.
    @Test
    @Order(6)
    public void testResponse3() throws Exception {
        this.mockMvc.perform(
                        post("/response3")
                                .param("id", "111")
                                .param("name","홍길동")
                )
                .andExpect( status().isOk() ) // 정상적으로 수행되는지 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.result").value("success")); // json 중에 result에 해당하는 값이 "success"인지 확인
    }


    // #7.
    @Test
    @Order(7)
    public void testResponse4() throws Exception {
        this.mockMvc.perform(
                        post("/response4")
                                .param("id", "111")
                                .param("name","홍길동")
                )
                .andExpect( status().isOk() ) // 정상적으로 수행되는지 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.result").value("success"))
                .andExpect( jsonPath("$.count").isEmpty()) // isEmpty: 빈 문자열인지 확인. (null인지. null이면 json에 빈문자ㅕㅇㄹ로 들어가니까)
                .andExpect( jsonPath("$.testUserDto.email").value("hog@g.com"))
                .andExpect( jsonPath("$.testUserDto.phone").isArray()); // 배열인지 확인. (controller에서의 List.)
    }
}
