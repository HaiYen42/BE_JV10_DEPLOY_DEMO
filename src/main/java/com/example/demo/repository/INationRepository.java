package com.example.demo.repository;

import com.example.demo.model.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationRepository extends JpaRepository<Nation, Long> {
    boolean existsByName(String name);
}
