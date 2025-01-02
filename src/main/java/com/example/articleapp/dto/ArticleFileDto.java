package com.example.articleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFileDto {
    private int id;
    private String filename;
    private String filepath;
    private long size;
    private int articleId;
}
