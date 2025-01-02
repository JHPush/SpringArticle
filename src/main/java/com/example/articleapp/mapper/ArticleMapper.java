package com.example.articleapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.articleapp.dto.ArticleDto;

@Mapper
public interface ArticleMapper {
    List<ArticleDto> selectArticleList();
    void insertArticle(ArticleDto articleDto);

    ArticleDto detailArticle(int id);

    void updateArticle(ArticleDto articleDto);

    void deleteArticle(int id);
    List<ArticleDto> findArticleList(Map<String,String> map);

}
