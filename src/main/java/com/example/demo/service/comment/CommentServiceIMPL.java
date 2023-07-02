package com.example.demo.service.comment;

import com.example.demo.model.Comment;
import com.example.demo.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentServiceIMPL implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Optional<Comment> findByFilmIdAndUserId(Long filmId, Long userId) {
        return commentRepository.findByFilmIdAndUserId(filmId, userId);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
