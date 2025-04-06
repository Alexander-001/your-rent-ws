package com.yourrent.your_rent_ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yourrent.your_rent_ws.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long id);
}
