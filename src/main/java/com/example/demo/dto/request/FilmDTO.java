package com.example.demo.dto.request;

import com.example.demo.model.Category;
import com.example.demo.model.Nation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String avatar;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    private String filmLink;
    @NotNull
    private List<Category>  categoryList;

    @NotNull
    private Nation nation;

}
