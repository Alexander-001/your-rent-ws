package com.yourrent.your_rent_ws.services;

import java.util.List;
import java.util.Optional;

import com.yourrent.your_rent_ws.models.User;
import com.yourrent.your_rent_ws.models.dto.UserRole;

public interface UserService {
    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(User user);

    Optional<User> update(Long id, User user);

    Optional<User> updateRoleAdmin(UserRole userRole);

    Optional<User> updateRoleUser(UserRole userRole);

    Optional<User> delete(Long id);

    boolean existsByEmail(String email);

    boolean existsByRut(String rut);

    boolean validatePasswords(String currentPassword, String newPassword);

}
