package com.mycom.myapp.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    // User 엔티티 가져올 때, 항상 userRole을 함께 가져오기.
    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();
}
