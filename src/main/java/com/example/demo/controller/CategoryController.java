package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Category;
import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.film.IFilmService;
import com.example.demo.service.implUser.IUserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IFilmService filmService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping
    private ResponseEntity<?> showListCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    // select * form category where id =?
    @GetMapping("/{id}")
    private ResponseEntity<?> detailCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    // insert into Cateogry () value()
    @PostMapping
    private ResponseEntity<?> createCategory(@RequestBody Category category){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        if (categoryService.existsByName(category.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_exist"), HttpStatus.OK);
        }
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("success"), HttpStatus.OK);
    }
    @GetMapping("/page")
    private ResponseEntity<?> pageCategory(@PageableDefault(size = 3)Pageable pageable){
        return new ResponseEntity<>(categoryService.findALl(pageable), HttpStatus.OK);
    }
    // update category set name = ? where id =?
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
        String role ="";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Category> category1 = categoryService.findById(id);
        if(!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!category.getAvatar().equals(category1.get().getAvatar())){
            category.setId(category1.get().getId());
        }
        if(!category.getName().equals(category1.get().getName())){
            if(categoryService.existsByName(category.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if(category.getName().equals(category1.get().getName())&& category.getAvatar().equals(category1.get().getAvatar())){
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        category.setId(category1.get().getId());
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }
    // delete from cateogory where id =?
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCategory(@PathVariable Long id){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Film> filmList = filmService.getFilmByCategoryId(id);
        for (int i = 0; i < filmList.size(); i++) {
            filmService.deleteById(filmList.get(i).getId());
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
}
