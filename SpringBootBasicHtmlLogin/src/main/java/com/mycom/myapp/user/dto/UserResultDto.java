package com.mycom.myapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

// @Builder 없음.
@Data
public class UserResultDto {
    private String result;
    private UserDto userDto;
    private List<UserDto> userList;
}
