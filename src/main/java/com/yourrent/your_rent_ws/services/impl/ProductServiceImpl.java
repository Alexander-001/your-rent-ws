package com.yourrent.your_rent_ws.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourrent.your_rent_ws.models.Product;
import com.yourrent.your_rent_ws.repositories.ProductRepository;
import com.yourrent.your_rent_ws.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productDb = productRepository.findById(id);
        if (productDb.isPresent()) {
            Product updateProduct = productDb.get();
            updateProduct.setName(product.getName());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setLocation(product.getLocation());
            updateProduct.setImages(product.getImages());
            updateProduct.setCategory(product.getCategory());
            updateProduct.setDatePublish(product.getDatePublish());
            updateProduct.setStatus(product.getStatus());
            return Optional.of(productRepository.save(updateProduct));
        }
        return productDb;
    }

    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productDb = productRepository.findById(id);
        productDb.ifPresent((prod) -> {
            productRepository.delete(prod);
        });
        return productDb;
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

}
