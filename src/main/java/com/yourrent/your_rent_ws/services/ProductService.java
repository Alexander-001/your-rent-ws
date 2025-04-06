package com.yourrent.your_rent_ws.services;

import java.util.List;
import java.util.Optional;

import com.yourrent.your_rent_ws.models.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);

    boolean existsById(Long id);

}
