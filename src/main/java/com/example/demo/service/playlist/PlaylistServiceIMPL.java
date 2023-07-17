package com.example.demo.service.playlist;

import com.example.demo.model.Film;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.repository.IPlaylistRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceIMPL implements IPlaylistService {
    @Autowired
    private IPlaylistRepository playlistRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public void save(Playlist playlist) {
        User user = userDetailService.getCurrentUser();
        playlist.setUser(user);
        playlistRepository.save(playlist);
    }

    @Override
    public Page<Playlist> findALl(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    public List<Playlist> findAllByUserId(Long id) {
        return playlistRepository.findAllByUserId(id);
    }

    @Override
    public List<Film> findByIdPlayList(Long id) {
        return playlistRepository.findByIdPlayList(id);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }
}
