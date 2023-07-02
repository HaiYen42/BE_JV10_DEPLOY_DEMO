package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Film {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob
    private String description;

    @Lob
    private String avatar;
    @NotNull
    // Object/ đối tượng
    private Date date =new Date();
    private Long view = 0L;
    @ManyToOne
    private User user;
    @ManyToOne
    private Nation nation;
    @NotNull
    private String filmLink;
    @OneToMany
            (fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private List<Like> likeList;
    @OneToMany
    @JoinColumn(name = "film_id")
    private List<Comment> commentList;

    @ManyToOne
    private Category category;

    public Film(String name, String avatar, String description, String filmLink, Category category, Nation nation) {
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.filmLink = filmLink;
        this.category = category;
        this.nation = nation;
    }
}
