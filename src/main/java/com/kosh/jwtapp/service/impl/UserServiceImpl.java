package com.kosh.jwtapp.service.impl;

import com.kosh.jwtapp.model.Role;
import com.kosh.jwtapp.model.User;
import com.kosh.jwtapp.model.enums.BaseEntityStatus;
import com.kosh.jwtapp.repository.RoleRepository;
import com.kosh.jwtapp.repository.UserRepository;
import com.kosh.jwtapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(BaseEntityStatus.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - {} succsesfully registered", registeredUser);
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("In getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null){
            log.warn("IN findById - no user found by id; {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
