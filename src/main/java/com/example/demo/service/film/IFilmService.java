package com.example.demo.service.film;

import com.example.demo.model.Film;
import com.example.demo.service.IGenericService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IFilmService extends IGenericService<Film> {
    Optional<Film> findById(Long id);

    void deleteById(Long id);
    Set<Film> getFilmImage();
    List<Film> getFilmByCategoryId(Long CategoryId);

    List<Film> getFilmByNationId(Long NationId);
    List<Film> findTop10ByOrderByViewDesc();
    void saveView(Film film);
    List<Film> findByNameContaining(String name);

}


