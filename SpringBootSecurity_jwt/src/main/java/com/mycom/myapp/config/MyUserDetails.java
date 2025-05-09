package com.mycom.myapp.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// 어플리케이션에 필요한 사용자 정보를 담음. 필요한거 더 추가해놓으면 됨. 기존에 세션에 담아줬던거랑 같음.
@AllArgsConstructor
@Builder
public class MyUserDetails implements UserDetails {


    private final String username;
    private final String password;
    private final String email; // extra field
    private final Collection<? extends GrantedAuthority> authorities;

    public String getEmail() {
        return email;
    }

    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    // 일단 복잡하지 않게 그냥 return true로 해놓음
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
