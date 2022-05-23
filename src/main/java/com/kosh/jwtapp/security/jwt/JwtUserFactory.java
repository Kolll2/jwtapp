package com.kosh.jwtapp.security.jwt;

import com.kosh.jwtapp.model.Role;
import com.kosh.jwtapp.model.User;
import com.kosh.jwtapp.model.enums.BaseEntityStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create (User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(BaseEntityStatus.ACTIVE),
                user.getUpdated(),
                mapToRantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToRantedAuthorities(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
