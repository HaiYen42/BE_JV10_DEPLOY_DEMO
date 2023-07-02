package com.example.demo.service.like;

import com.example.demo.model.Like;
import com.example.demo.repository.ILikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LikeServiceIMPL implements ILikeService{
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Optional<Like> findByFilmIdAndUserId(Long filmId, Long userId) {
        return likeRepository.findByFilmIdAndUserId(filmId, userId);
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
