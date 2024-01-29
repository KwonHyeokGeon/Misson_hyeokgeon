package com.example.mission.dto;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String password;
    private LocalDateTime writtenDate;
    private Board board;
    public ArticleDto(String title, String content, String password, LocalDateTime writtenDate) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.writtenDate = writtenDate;
    }

    public static ArticleDto from(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.title = article.getTitle();
        articleDto.content = article.getContent();
        articleDto.password = article.getPassword();
        articleDto.writtenDate = article.getWrittenDate();
        articleDto.board = article.getBoard();
        return articleDto;
    }
}