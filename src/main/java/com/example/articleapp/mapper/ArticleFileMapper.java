package com.example.articleapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.articleapp.dto.ArticleFileDto;

@Mapper
public interface ArticleFileMapper {
    void insertArticleFile(List<ArticleFileDto> files);

}
