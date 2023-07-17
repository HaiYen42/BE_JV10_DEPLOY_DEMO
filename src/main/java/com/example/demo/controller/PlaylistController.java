package com.example.demo.controller;

import com.example.demo.dto.request.PlaylistDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Film;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.film.IFilmService;
import com.example.demo.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IFilmService filmService;

    @GetMapping
    public ResponseEntity<?> getListPlaylist(){
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createPlayList(@RequestBody Playlist playlist){
        playlistService.save(playlist);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id){
        Optional<Playlist> playlist = playlistService.findById(id);
        if (!playlist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }
    @GetMapping("/playlist_user")
    public ResponseEntity<?> getPlaylistByUserId(){
        User user = userDetailService.getCurrentUser();
        return new ResponseEntity<>(playlistService.findAllByUserId(user.getId()), HttpStatus.OK);
    }
    @PostMapping("/add-film")
    public ResponseEntity<?> addFilmToPlaylist(@RequestBody PlaylistDTO playlistDTO){
        Optional<Film> film = filmService.findById(playlistDTO.getFilm_id());
        Optional<Playlist> playlist = playlistService.findById(playlistDTO.getPlaylist_id());
        List<Film> filmList = new ArrayList<>();
        filmList = playlistService.findByIdPlayList(playlistDTO.getPlaylist_id());
        filmList.add(film.get());
        playlist.get().setFilmList(filmList);
        playlistService.save(playlist.get());
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }
    @PutMapping ("/delete_film")
    public ResponseEntity<?> deleteFilmInPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Optional<Film> film = filmService.findById(playlistDTO.getFilm_id());
        Optional<Playlist> playlist = playlistService.findById(playlistDTO.getPlaylist_id());
        List<Film> filmList = new ArrayList<>();
        List<Film> filmAfter = new ArrayList<>();
        filmList = playlistService.findByIdPlayList(playlistDTO.getPlaylist_id());
        for (int i = 0; i < filmList.size(); i++) {
            if (filmList.get(i).getId().longValue()==film.get().getId().longValue()) {
                continue;
            }
            filmAfter.add(filmList.get(i));
        }
        playlist.get().setFilmList(filmAfter);
        playlistService.save(playlist.get());
        return new ResponseEntity<>(new ResponMessage("delete_sucess"), HttpStatus.OK);
    }

}
