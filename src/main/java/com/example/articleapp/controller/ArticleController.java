package com.example.articleapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.articleapp.dto.ArticleDto;
import com.example.articleapp.service.ArticleService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {
    
    private final ArticleService articleService;

    // 게시글 조회
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getArticleList() {
        List<ArticleDto> articles = articleService.retrieveArticleList();

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    // 게시글 등록 요청
    // @PostMapping("/articles")
    // public ResponseEntity<Map<String, Integer>> postArticle(@RequestBody
    // ArticleDto articleDto) {
    // int id = articleService.registerArticle(articleDto);
    // Map<String, Integer> map = new HashMap<>();
    // map.put("id", id);
    // return new ResponseEntity<>(map, HttpStatus.CREATED);
    // }

    @PostMapping("/articles")
    public ResponseEntity<Map<String, Integer>> postArticle(
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestPart(value = "article") ArticleDto articleDto) {
        int articleId = articleService.registerArticle(articleDto, files);
        return new ResponseEntity<>(Map.of("id", articleId), HttpStatus.CREATED);
    }

    // 게시글 상세 조회 요청
    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") int id) {
        return new ResponseEntity<>(articleService.retrieveArticle(id), HttpStatus.OK);
    }

    // 게시글 수정 요청
    @PutMapping("/article/{id}")
    public ResponseEntity<String> putArticle(@PathVariable("id") int id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.modifyArticle(articleDto);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    // 게시글 삭제 요청
    @DeleteMapping("/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") int id) {
        articleService.removeArticle(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    // 게시글 검색 요청
    @GetMapping("/search")
    public ResponseEntity<List<ArticleDto>> searchArticle(
            @RequestParam(name = "keyField", required = false, defaultValue = "") String keyField,
            @RequestParam(name = "keyWord", required = false, defaultValue = "") String keyWord) {
        Map<String, String> map = new HashMap<>();
        map.put("keyField", keyField); // writer, title, content : 검색조건
        map.put("keyWord", keyWord); // 검색어
        List<ArticleDto> articles = articleService.search(map);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename){
        return articleService.download(filename);        
    }
}
