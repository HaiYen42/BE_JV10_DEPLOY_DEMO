package com.example.demo.service.history;

import com.example.demo.model.History;
import com.example.demo.repository.IHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryServiceIMPL implements IHistoryService {
    @Autowired
    private IHistoryRepository historyRepository;

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }

    @Override
    public Optional<History> findByUser_id(Long id) {
        return historyRepository.findHistoryByUser_Id(id);
    }
}
