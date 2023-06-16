package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenericService <T>{
    List<T> findAll();
    void save(T t);
    Page<T> findALl(Pageable pageable);
}
