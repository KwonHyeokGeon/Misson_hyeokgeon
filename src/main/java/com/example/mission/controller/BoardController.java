package com.example.mission.controller;

import com.example.mission.dto.ArticleDto;
import com.example.mission.service.ArticleService;
import com.example.mission.service.BoardService;
import com.example.mission.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    public String readAllCategory(Model model) {
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("articles", articleService.readAll());
        return "boards";
    }

    @GetMapping("/{boardId}")
    public String readAllArticle(@PathVariable Long boardId, Model model) {
        model.addAttribute("articles", articleService.readAllByBoardId(boardId));
        return "articles";
    }

    @PostMapping("/{boardId}/article")
    public String createArticle(@PathVariable Long boardId, ArticleDto articleDto) {
        Long articleId = articleService.create(articleDto, boardId);
        return "redirect:/boards/" + boardId + "/article/" + articleId;
    }
}

