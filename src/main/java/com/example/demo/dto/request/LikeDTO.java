package com.example.demo.dto.request;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeDTO {
    private Film film;
//    private User user;
}
