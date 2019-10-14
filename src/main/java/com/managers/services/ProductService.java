package com.managers.services;

import com.managers.models.Product;

public interface ProductService {
    Iterable<Product> findAll();

    Product findById(Long id);

    Product save(Product customer);

    void remove(Long id);

    void removeAll();
}
