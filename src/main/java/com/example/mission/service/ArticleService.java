package com.example.mission.service;

import com.example.mission.dto.ArticleDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.repository.ArticleRepository;
import com.example.mission.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    // 게시글 작성
    public Long create(ArticleDto articleDto, Long boardId) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setPassword(articleDto.getPassword());
        article.setWrittenDate(LocalDateTime.now());
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시판 없음"));
        article.setBoard(board);
        ArticleDto from = ArticleDto.from(articleRepository.save(article));
        return from.getId();
    }

    // 카테고리에 해당하는 게시글 전부 조회
    public List<ArticleDto> readAllByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findAllByBoardIdOrderByWrittenDateDesc(boardId);
        List<ArticleDto> articleDto = new ArrayList<>();
        for (Article article : articles) {
            articleDto.add(ArticleDto.from(article));
        }
        return articleDto;
    }

    // 전체 게시글 조회
    public List<ArticleDto> readAll() {
        Page<Article> articles = articleRepository.findAllByOrderByIdDesc(PageRequest.of(0,15));
        List<ArticleDto> articleDto = new ArrayList<>();
        for (Article article : articles) {
            articleDto.add(ArticleDto.from(article));
        }
        return articleDto;
    }

    // 단일 게시글 조회
    public ArticleDto readArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        return ArticleDto.from(article);
    }

    // 게시글 업데이트 - save메서드 없이 dirty check로
    @Transactional
    public Long update(ArticleDto articleDto, Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        if (!articleDto.getPassword().equals(article.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        ArticleDto from = ArticleDto.from(article);
        return from.getId();
    }

    public ArticleDto delete(ArticleDto articleDto, Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        if (article.getPassword().equals(articleDto.getPassword())) {
            articleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지않음");
        }
        return ArticleDto.from(article);
    }

    public boolean passwordCheck(Long articleId, String password) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("없음"));
        return article.getPassword().equals(password);
    }
}
