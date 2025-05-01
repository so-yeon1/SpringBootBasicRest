package com.mycom.myapp.user.dto;

import lombok.Data;

@Data
public class TestResultDto {
    private String result;
    private Long count;
    private TestUserDto testUserDto;
}
