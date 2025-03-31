package com.yourrent.your_rent_ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yourrent.your_rent_ws.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
