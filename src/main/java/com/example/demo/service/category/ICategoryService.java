package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.IGenericService;

import java.util.Optional;

public interface ICategoryService extends IGenericService<Category> {
    boolean existsByName(String name);

    Optional<Category> findById(Long id);

    void deleteById(Long id);
}
