package com.example.mission.controller;

import com.example.mission.dto.CommentDto;
import com.example.mission.entity.Comment;
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
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public String readOne(@PathVariable Long articleId, Model model) {
        model.addAttribute("article", articleService.readArticle(articleId));
        model.addAttribute("comments", commentService.readAll());
        return "article";
    }

    @PostMapping("/{articleId}/comment")
    public String commentWrite(@PathVariable Long articleId, CommentDto commentDto) {
        commentService.create(commentDto, articleId);
        return "redirect:/articles/" + articleId;
    }
}
