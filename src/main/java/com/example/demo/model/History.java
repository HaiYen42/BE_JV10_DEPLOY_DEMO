package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "film_history", joinColumns = @JoinColumn(name = "history_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> history;
    @OneToOne
    private User user;
}
