package com.example.demo.controller;

import com.example.demo.dto.request.LikeDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Film;
import com.example.demo.model.Like;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.film.IFilmService;
import com.example.demo.service.like.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "*")
public class LikeController {
    @Autowired
    private ILikeService likeService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IFilmService filmService;
    @PostMapping
    public ResponseEntity<?> Like(@RequestBody LikeDTO likeDTO){
        User user = userDetailService.getCurrentUser();
        if (user.getId() == null) {
            return new ResponseEntity<>(new ResponMessage("no_login"), HttpStatus.OK);
        }

        Optional<Film> film = filmService.findById(likeDTO.getFilm().getId());
        if (!film.isPresent()){
            return new ResponseEntity<>(new ResponMessage("ko co phim"), HttpStatus.OK);
        }
        Optional<Like> like = likeService.findByFilmIdAndUserId(likeDTO.getFilm().getId(),user.getId());
        if (!like.isPresent()) {
            Like like1 = new Like(user, film.get());
            likeService.save(like1);
            return new ResponseEntity<>(new ResponMessage("like_ok"), HttpStatus.OK);
        }
        likeService.deleteById(like.get().getId());
        return new ResponseEntity<>(new ResponMessage("unlike_ok"), HttpStatus.OK);
    }

}
