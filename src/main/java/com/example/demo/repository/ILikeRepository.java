package com.example.demo.repository;

import com.example.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByFilmIdAndUserId(Long filmId, Long userId);

}
