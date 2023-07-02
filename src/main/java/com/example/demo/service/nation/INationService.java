package com.example.demo.service.nation;

import com.example.demo.model.Nation;
import com.example.demo.service.IGenericService;

import java.util.Optional;

public interface INationService extends IGenericService<Nation> {
    void deleteById(Long id);
    boolean existsByName(String name);
    Optional<Nation> findById(Long id);
}
