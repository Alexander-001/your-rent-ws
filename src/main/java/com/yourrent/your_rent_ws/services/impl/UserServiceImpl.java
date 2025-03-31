package com.yourrent.your_rent_ws.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yourrent.your_rent_ws.models.Role;
import com.yourrent.your_rent_ws.models.User;
import com.yourrent.your_rent_ws.models.UserRole;
import com.yourrent.your_rent_ws.repositories.RoleRepository;
import com.yourrent.your_rent_ws.repositories.UserRepository;
import com.yourrent.your_rent_ws.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // * Get all users
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    // * Get user by id
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // * Save user in databse with role USER or ADMIN
    @Transactional
    @Override
    public User save(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(role -> roles.add(role));
        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(role -> roles.add(role));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // * Get user by id and update with new data
    @Transactional
    @Override
    public Optional<User> update(Long id, User user) {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            User updateUser = userDb.get();
            updateUser.setRut(user.getRut());
            updateUser.setEmail(user.getEmail());
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setPhone(user.getPhone());
            updateUser.setBirthDate(user.getBirthDate());
            updateUser.setCountry(user.getCountry());
            updateUser.setCity(user.getCity());
            updateUser.setAddress(user.getAddress());
            updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updateUser.setImage(user.getImage());
            return Optional.of(userRepository.save(updateUser));
        }
        return userDb;
    }

    // * Add ROLE_ADMIN to user
    @Transactional
    @Override
    public Optional<User> updateRoleAdmin(UserRole userRole) {
        Optional<User> userDb = userRepository.findByEmail(userRole.getEmail());
        User user = userDb.get();
        List<Role> roles = user.getRoles();
        List<String> roleName = roles.stream().map(Role::getName).toList();
        System.out.println(roleName);
        if (!roleName.contains("ROLE_ADMIN")) {
            Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");
            Role adminRole;
            if (optionalRole.isPresent()) {
                adminRole = optionalRole.get();
            } else {
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                adminRole = roleRepository.save(adminRole);
            }
            roles.add(adminRole);
            user.setRoles(roles);
            userRepository.save(user);
        }
        return Optional.of(user);
    }

    // * Remove ROLE_ADMIN to user
    @Transactional
    @Override
    public Optional<User> updateRoleUser(UserRole userRole) {
        Optional<User> userDb = userRepository.findByEmail(userRole.getEmail());
        User user = userDb.get();
        System.out.println(user);
        List<Role> roles = user.getRoles();
        List<String> roleName = roles.stream().map(Role::getName).toList();
        if (roleName.contains("ROLE_ADMIN")) {
            Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");
            if (optionalRole.isPresent()) {
                Role adminRole = optionalRole.get();
                roles.remove(adminRole);
                user.setRoles(roles);
                userRepository.save(user);
            }
        }
        return Optional.of(user);
    }

    // * Delete user by id
    @Transactional
    @Override
    public Optional<User> delete(Long id) {
        Optional<User> userDb = userRepository.findById(id);
        userDb.ifPresent(user -> {
            roleRepository.deleteById(id);
            userRepository.delete(user);
        });
        return userDb;
    }

    // * Validate if exists email
    @Transactional
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // * Validate if exists rut
    @Override
    public boolean existsByRut(String rut) {
        return userRepository.existsByRut(rut);
    }

    @Transactional
    @Override
    public boolean validatePasswords(String currentPassword, String newPassword) {
        return passwordEncoder.matches(newPassword, currentPassword);
    }
}
