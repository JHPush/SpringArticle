package com.example.articleapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(of = {"id", "title", "content", "writer", "regDate"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private int id;

    @NotBlank(message = "title cannot be empty")
    @Size(max = 50, message = "title cannot excute 50 characters")
    private String title;

    @NotBlank(message = "content cannot be empty")
    private String content;

    @NotBlank(message = "writer cannot be empty")
    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    private UsersDto usersDto;
    // private UserDto userDto;
    private List<ArticleFileDto> files = new ArrayList<>();

}
