package com.example.mission.repository;

import com.example.mission.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByBoardIdOrderByWrittenDateDesc(Long boardId);

//    List<Article> findAllByOrderByIdDesc();

    Page<Article> findAllByOrderByIdDesc(Pageable pageable);
}
