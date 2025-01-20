package com.example.articleapp.exception;

// Unchecked Exception
public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(){
        super("게시글 정보가 존재하지 않음");
    }
    public ArticleNotFoundException(String message){
        super(message);
    }
    
}
