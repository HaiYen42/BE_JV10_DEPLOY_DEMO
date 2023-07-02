package com.example.demo.service.comment;

import com.example.demo.model.Comment;

import java.util.Optional;

public interface ICommentService {
    Optional<Comment> findByFilmIdAndUserId(Long filmId, Long userId);
    void save(Comment comment);
    void deleteById(Long id);
}
