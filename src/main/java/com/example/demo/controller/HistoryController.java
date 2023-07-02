package com.example.demo.controller;

import com.example.demo.service.history.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlist")
@CrossOrigin(origins = "*")
public class HistoryController {
    @Autowired
    private IHistoryService playlistService;
//    public ResponseEntity<?> addPlaylist(){
//
//    }
}
