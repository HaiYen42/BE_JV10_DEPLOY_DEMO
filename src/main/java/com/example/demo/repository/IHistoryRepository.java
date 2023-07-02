package com.example.demo.repository;

import com.example.demo.model.Film;
import com.example.demo.model.History;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHistoryRepository extends JpaRepository<History, Long> {

Optional<History> findHistoryByUser_Id(Long id);

}
