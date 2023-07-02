package com.example.demo.controller;

import com.example.demo.dto.request.CommentDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Comment;
import com.example.demo.model.Film;
import com.example.demo.model.Like;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.comment.ICommentService;
import com.example.demo.service.film.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IFilmService filmService;
    @PostMapping
    public ResponseEntity<?> comment(@Valid @RequestBody CommentDTO commentDTO, BindingResult result){
        User user = userDetailService.getCurrentUser();
        if (user.getId() == null) {
            return new ResponseEntity<>(new ResponMessage("no_login"), HttpStatus.OK);
        }
        Optional<Film> film = filmService.findById(commentDTO.getFilm().getId());
        if (!film.isPresent()){
            return new ResponseEntity<>(new ResponMessage("film_not_existed"), HttpStatus.OK);
        }
        if (result.hasErrors()){
            return new ResponseEntity<>(new ResponMessage("content_not_existed"), HttpStatus.NOT_FOUND);
        }
        Comment comment = new Comment(commentDTO.getContent(), user, film.get());
        commentService.save(comment);
        return new ResponseEntity<>(new ResponMessage("comment_ok"), HttpStatus.OK);
    }
}
