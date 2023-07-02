package com.example.demo.repository;

import com.example.demo.model.Film;
import com.example.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFilmRepository extends JpaRepository<Film, Long> {
        List<Film> findTop10ByOrderByViewDesc();
        List<Film> findByNameContaining(String name);
}