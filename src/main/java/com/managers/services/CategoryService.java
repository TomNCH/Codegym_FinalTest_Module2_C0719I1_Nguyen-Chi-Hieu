package com.managers.services;

import com.managers.models.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);
}
