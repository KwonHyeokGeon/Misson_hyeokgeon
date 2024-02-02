package com.example.mission.dto;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;
    private LocalDateTime writtenDate;
    private Board board;

    public ArticleDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public static ArticleDto from(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.id = article.getId();
        articleDto.title = article.getTitle();
        articleDto.content = article.getContent();
        articleDto.password = article.getPassword();
        articleDto.writtenDate = article.getWrittenDate();
        articleDto.board = article.getBoard();
        return articleDto;
    }
}