package com.yourrent.your_rent_ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yourrent.your_rent_ws.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByRut(String rut);

    Optional<User> findByEmail(String email);

    Optional<User> findByRut(String rut);
}
