package com.example.taskmanager.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TaskEntity> tasks;

    // If you're not using roles/authorities yet, return an empty list
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    // These should return true if you want the account to be usable
    @Override
    public boolean isAccountNonExpired() {
        return true; // no expiry logic implemented
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // assuming account isn't locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // credentials are valid indefinitely for now
    }

    @Override
    public boolean isEnabled() {
        return true; // user is enabled and allowed to log in
    }
}
