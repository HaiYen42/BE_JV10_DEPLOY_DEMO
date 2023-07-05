package com.example.demo.controller;


import com.example.demo.dto.request.FilmDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Film;
import com.example.demo.model.History;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.film.IFilmService;
import com.example.demo.service.history.IHistoryService;
import com.example.demo.service.implUser.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private IFilmService filmService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IHistoryService historyService;

    @GetMapping
    private ResponseEntity<?> listFilm() {
        return new ResponseEntity<>(filmService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> detailFilm(@PathVariable Long id) {
        Optional<Film> film = filmService.findById(id);
        User user = userDetailService.getCurrentUser();
        List<Film> films = null;
        if (!film.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        film.get().setView(film.get().getView() + 1);
//        filmService.saveView(film.get());
        return new ResponseEntity<>(film, HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    private ResponseEntity<?> increaseView(@PathVariable Long id) {
        Optional<Film> film = filmService.findById(id);
//        User user = userDetailService.getCurrentUser();
//        List<Film> films = null;
        if (!film.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        film.get().setView(film.get().getView() + 1);
        filmService.saveView(film.get());
        return new ResponseEntity<>(film, HttpStatus.OK);
    }


    @GetMapping("/page")
    private ResponseEntity<?> pageFilm(Pageable pageable) {
        return new ResponseEntity<>(filmService.findALl(pageable), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createFilm(@Valid @RequestBody FilmDTO filmDTO) {
        String role ="";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Film film = new Film(filmDTO.getName(), filmDTO.getAvatar(), filmDTO.getDescription(), filmDTO.getFilmLink(), filmDTO.getCategoryList(), filmDTO.getNation());
        filmService.save(film);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> editFilm(@PathVariable Long id, @RequestBody FilmDTO film) {
        String role ="";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Film> film1 = filmService.findById(id);
        if (!film1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (film.getName().equals(film1.get().getName()) &&
                film.getAvatar().equals(film1.get().getAvatar()) &&
                film.getDescription().equals(film1.get().getDescription()) &&
                film.getFilmLink().equals(film1.get().getFilmLink()) &&
                film.getNation().getId() == film1.get().getNation().getId()) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        film1.get().setName(film.getName());
        film1.get().setAvatar(film.getAvatar());
        film1.get().setDescription(film.getDescription());
        film1.get().setFilmLink(film.getFilmLink());
        film1.get().setCategoryList(film.getCategoryList());
        film1.get().setNation(film.getNation());
        filmService.save(film1.get());
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        String role ="";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Film> film = filmService.findById(id);
        if (!film.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }

    @GetMapping("/filmImg")
    private ResponseEntity<?> getFilmImg() {
        Set<Film> filmSet = filmService.getFilmImage();
        List<Film> filmList = new ArrayList<>(filmSet);
        return new ResponseEntity<>(filmList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    private ResponseEntity<?> getFilmByCategoryId(@PathVariable Long id) {
        List<Film> films = filmService.getFilmByCategoryId(id);
        if (films.isEmpty()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/nation/{id}")
//    @PreAuthorize()
    private ResponseEntity<?> getFilmByNationId(@PathVariable Long id) {
        List<Film> films = filmService.getFilmByNationId(id);
        if (films.isEmpty()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/views")
    private ResponseEntity<?> getTopFilm() {
        List<Film> films = filmService.findTop10ByOrderByViewDesc();
        if (films.isEmpty()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    // select * form film where name like '%name%'
    @GetMapping("/search")
    private ResponseEntity<?> getListSearchBandByName(@RequestParam String name) {
        List<Film> films = filmService.findByNameContaining(name);
        if (films.isEmpty()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
}
