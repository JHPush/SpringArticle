package com.example.articleapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.articleapp.dto.ArticleDto;
import com.example.articleapp.dto.ArticleFileDto;
import com.example.articleapp.mapper.ArticleFileMapper;
import com.example.articleapp.mapper.ArticleMapper;
import com.example.articleapp.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 파이널 등 NON null 변수들의 생성자를 자동 생성
public class ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleFileMapper articleFileMapper;
    private final CustomFileUtil customFileUtil;

    // 게시글 목록 조회하다
    public List<ArticleDto> retrieveArticleList(){
        return articleMapper.selectArticleList();
    }

    @Transactional(readOnly = false)
    public int registerArticle(ArticleDto articleDto, List<MultipartFile> files){
        // articleMapper.insertArticle(articleDto);
        // return articleDto.getId();
        articleMapper.insertArticle(articleDto);
        List<ArticleFileDto> articleFiles = customFileUtil.saveFiles(files);

        if(articleFiles == null){
            return articleDto.getId();
        }
        
        for(ArticleFileDto articleFileDto : articleFiles){
            articleFileDto.setArticleId(articleDto.getId());
        }
        articleFileMapper.insertArticleFile(articleFiles);

        return articleDto.getId();
    }

    public ArticleDto retrieveArticle(int id){
        return articleMapper.detailArticle(id);
    }

    @Transactional(readOnly = false)
    public void modifyArticle(ArticleDto articleDto){
        articleMapper.updateArticle(articleDto);
    }
    
    @Transactional(readOnly = false)
    public void removeArticle(int id){
        articleMapper.deleteArticle(id);
    }

    public List<ArticleDto> search(Map<String,String> map ) {
        return articleMapper.findArticleList(map);
    }

    // 파일 다운로드 
    public ResponseEntity<Resource> download(String filename){
        return customFileUtil.download(filename);
    }
}
