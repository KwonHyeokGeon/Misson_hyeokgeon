package com.example.mission.repository;

import com.example.mission.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByBoardIdOrderByWrittenDateDesc(Long boardId);

    List<Article> findAllByOrderByWrittenDateDesc();
}
