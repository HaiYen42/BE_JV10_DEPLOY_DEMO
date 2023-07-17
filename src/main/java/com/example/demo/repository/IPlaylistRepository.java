package com.example.demo.repository;

import com.example.demo.model.Film;
import com.example.demo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
   List<Playlist> findAllByUserId(Long userId);
   @Query("select pll.filmList from Playlist pll where pll.id =:id")
   List<Film> findByIdPlayList(Long id);
}
