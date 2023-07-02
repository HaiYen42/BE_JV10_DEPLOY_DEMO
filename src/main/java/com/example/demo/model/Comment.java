package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Film film;

    public Comment(String content, User user, Film film) {
        this.content = content;
        this.user = user;
        this.film = film;
    }


}
