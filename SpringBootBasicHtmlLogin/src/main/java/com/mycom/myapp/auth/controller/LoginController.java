package com.mycom.myapp.auth.controller;

import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public UserResultDto login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session ) {

        UserResultDto userResultDto = loginService.login(email, password);

        // 로그인 성공시 session에 담기.
        if(userResultDto.getResult().equals("success")) {
            session.setAttribute("userDto", userResultDto.getUserDto());
        }
        return userResultDto;
    }

    @GetMapping("/logout")
    public UserResultDto logout(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();

        session.invalidate();
        userResultDto.setResult("success");

        return userResultDto;
    }
}
