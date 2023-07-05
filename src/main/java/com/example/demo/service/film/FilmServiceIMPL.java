package com.example.demo.service.film;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.repository.IFilmRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceIMPL implements IFilmService{
    @Autowired
    private IFilmRepository filmRepository;

    @Autowired
    private UserDetailService userDetailService;
    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public void save(Film film) {
        User user = userDetailService.getCurrentUser();
        film.setUser(user);
        filmRepository.save(film);
    }

    @Override
    public Page<Film> findALl(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    @Override
    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Set<Film> getFilmImage() {
        List<Film> filmList = filmRepository.findAll();
        Set<Film> filmSet = new HashSet<>();
        int number;
        for (int i = 0; i < 15; i++) {
            number= (int) Math.floor(filmList.size()*Math.random());
            filmSet.add(filmList.get(number));
            if (filmSet.size()==3) {
                break;
            }
        }
        return filmSet;
    }

    @Override
    public List<Film> getFilmByCategoryId(Long categoryId) {
        List<Film> filmList = filmRepository.findAll();
        List<Film> filmsByCategory = new ArrayList<>();
        for (int i = 0; i < filmList.size(); i++) {
            for (int j = 0; j < filmList.get(i).getCategoryList().size(); j++) {
                if (filmList.get(i).getCategoryList().get(j).getId().longValue()== categoryId.longValue()){
                    filmsByCategory.add(filmList.get(i));
                }
            }

        }
        return filmsByCategory;
    }

    @Override
    public List<Film> getFilmByNationId(Long nationId) {
        List<Film> filmList = filmRepository.findAll();
        List<Film> filmsByNation = new ArrayList<>();
        for (int i = 0; i < filmList.size(); i++) {
            if (filmList.get(i).getNation().getId()== nationId){
                filmsByNation.add(filmList.get(i));
            }
        }
        return filmsByNation;
    }

    @Override
    public List<Film> findTop10ByOrderByViewDesc() {
        return filmRepository.findTop10ByOrderByViewDesc();
    }

    @Override
    public void saveView(Film film) {
        filmRepository.save(film);
    }

    @Override
    public List<Film> findByNameContaining(String name) {
        return filmRepository.findByNameContaining(name);
    }


}
