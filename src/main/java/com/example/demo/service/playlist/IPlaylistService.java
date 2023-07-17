package com.example.demo.service.playlist;

import com.example.demo.model.Film;
import com.example.demo.model.Playlist;
import com.example.demo.service.IGenericService;

import java.util.List;
import java.util.Optional;

public interface IPlaylistService extends IGenericService<Playlist> {
    List<Playlist> findAllByUserId(Long id);
    List<Film> findByIdPlayList(Long id);

    Optional<Playlist> findById(Long id);
}
