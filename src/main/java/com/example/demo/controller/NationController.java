package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Film;
import com.example.demo.model.Nation;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.film.IFilmService;
import com.example.demo.service.implUser.IUserService;
import com.example.demo.service.nation.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/nation")
public class NationController {
    @Autowired
    private INationService nationService;
    @Autowired
    private IFilmService filmService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;

    @GetMapping
    private ResponseEntity<?> listNation() {
        return new ResponseEntity<>(nationService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> getNationById(@PathVariable Long id){
        Optional<Nation> nation = nationService.findById(id);
        if (!nation.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("not_existed"), HttpStatus.OK);
        }
        return new ResponseEntity<>(nation, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createNation(@RequestBody Nation nation) {

        if (nationService.existsByName(nation.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
        }
        nationService.save(nation);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }
    @GetMapping("/page")
    private ResponseEntity<?> pageNation(Pageable pageable){
        return new ResponseEntity<>(nationService.findALl(pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteNation(@PathVariable Long id){
        Optional<Nation> nation = nationService.findById(id);
        if (!nation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Film> filmList = filmService.getFilmByNationId(id);
        for (int i = 0; i < filmList.size(); i++) {
            filmService.deleteById(filmList.get(i).getId());
        }
        nationService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    private ResponseEntity<?> editNation(@PathVariable Long id, @RequestBody Nation nation){
        Optional<Nation> nation1 = nationService.findById(id);
        if (!nation1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (nation1.get().getName().equals(nation.getName())){
                return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        if (!nation1.get().getName().equals(nation.getName())){
            if (nationService.existsByName(nation.getName())){
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        nation.setId(nation1.get().getId());
        nationService.save(nation);
        return new ResponseEntity<>(new ResponMessage("success"), HttpStatus.OK);
    }
}
