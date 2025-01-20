package com.example.articleapp.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.articleapp.dto.ArticleDto;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@Transactional
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
public class ArticleMapperTest {
    @Autowired
    private ArticleMapper testMapper;

    @Test
    public void testSelectArticleList(){
        // assertNull(testMapper);

        // Given - 테스트 데이터 준비 (설정)
        
        // When - 실제 테스트 코드 진행
        List<ArticleDto> articles = testMapper.selectArticleList();

        // Then - 결과 검증
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
        assertEquals(7, articles.size());
    }

    @Test
    @Rollback(value = true)
    public void testInsertArticle(){
        ArticleDto dtd = new ArticleDto();
        dtd.setTitle("3i39");
        dtd.setContent("333");
        dtd.setWriter("ddd");
        testMapper.insertArticle(dtd);
    }

    @Test
    @Rollback(value = true)
    public void testModifyArticle(){
        ArticleDto dto = testMapper.detailArticle(5);
        dto.setContent("히히히히");
        testMapper.updateArticle(dto);
        assertEquals( testMapper.detailArticle(5), dto);

    }
}
