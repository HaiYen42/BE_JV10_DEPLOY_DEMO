package com.example.demo.service.like;

import com.example.demo.model.Like;
import com.example.demo.service.IGenericService;

import java.util.Optional;

public interface ILikeService {
    Optional<Like> findByFilmIdAndUserId(Long filmId, Long userId);
    void save(Like like);
    void deleteById(Long id);
}
