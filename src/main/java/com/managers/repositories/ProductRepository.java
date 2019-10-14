package com.managers.repositories;

import com.managers.models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product>findAllByName(String name);
}
