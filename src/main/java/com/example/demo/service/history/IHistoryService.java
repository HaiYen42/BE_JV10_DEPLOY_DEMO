package com.example.demo.service.history;

import com.example.demo.model.History;

import java.util.Optional;

public interface IHistoryService {
    void save(History history);
    Optional<History> findByUser_id(Long id);
}
