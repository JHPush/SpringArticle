package com.example.articleapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.articleapp.dto.ArticleDto;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest // 통합테스트 (빈설정, 의존성주입한것 등 모든것들이 노드가 됨)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
public class ArticleServiceTest {
    @Autowired
    private ArticleService testService;
    
    @Test
    public void checkNull(){
        assertNotNull(testService);
    }
    @Test
    public void checkArticles(){
        // given
        
        // when
        List<ArticleDto> lists = testService.retrieveArticleList();

        // then
        assertNotEquals(0, lists.size());
        lists.forEach((article ->{
            System.out.println(article);
        }));
    }
    @Test
    public void testSearch(){
        Map<String, String> searchData = new HashMap<String,String>();
        searchData.put("keyField", "content");
        searchData.put("keyWord", "내용");
       List<ArticleDto> lists =  testService.search(searchData);
       lists.forEach((article->{
        System.out.println(article);
       }));
    }
}
