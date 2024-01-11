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

    @GetMapping
    public String readAllCategory(Model model) {
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("articles", articleService.readAll());
        return "boards";
    }

    @GetMapping("/{boardId}")
    public String readAllArticle(@PathVariable Long boardId, Model model) {
        model.addAttribute("articles", articleService.readAllByBoardId(boardId));
        model.addAttribute("category", boardService.read(boardId).getCategory());
        return "articles";
    }

    @GetMapping("/{boardId}/article")
    public String articleWriteForm(@PathVariable Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "articleForm";
    }

    // 게시글 작성
    @PostMapping("/{boardId}/article/new")
    public String createArticle(@PathVariable Long boardId, ArticleDto articleDto) {
        Long articleId = articleService.create(articleDto, boardId);
        return "redirect:/articles/" + articleId;
    }
}

