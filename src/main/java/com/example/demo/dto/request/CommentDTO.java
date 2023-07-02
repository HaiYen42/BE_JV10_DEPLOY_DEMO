package com.example.demo.dto.request;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    @NotBlank
    @NotNull
    private String content;
    private Film film;
}
