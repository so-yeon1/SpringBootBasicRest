package com.mycom.myapp.user.repository;

import com.mycom.myapp.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    // crud는 자동구현 (작성할 필요X)

    // 이름으로 검색, 영속화
    UserRole findByName(String name);
}
