package com.mycom.myapp.user.dto;

import com.mycom.myapp.user.entity.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    // <role id, role nmae>
    private List<UserRole> roles;
}
