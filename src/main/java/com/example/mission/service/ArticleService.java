package com.example.mission.service;

import com.example.mission.dto.ArticleDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.repository.ArticleRepository;
import com.example.mission.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public Long create(ArticleDto articleDto, Long boardId){
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setPassword(articleDto.getPassword());
        article.setWrittenDate(LocalDateTime.now());
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시판 없음"));
        article.setBoard(board);
        articleRepository.save(article);
        return article.getId();
    }

    // 카테고리에 해당하는 게시글 전부 조회
    public List<Article> readAllByBoardId(Long boardId) {
        return articleRepository.findAllByBoardIdOrderByWrittenDateDesc(boardId);
    }

    // 전체 게시글 조회
    public List<Article> readAll(){
        return articleRepository.findAllByOrderByWrittenDateDesc();
    }

    // 단일 게시글 조회
    public Article readArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("게시글 없음"));
    }
}
