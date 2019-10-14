package com.managers.services;

import com.managers.models.Product;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Iterable<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void remove(Long id);

    Iterable<Product> findAllByName(String name);
}
