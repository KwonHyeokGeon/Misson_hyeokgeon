package com.example.mission.controller;

import com.example.mission.dto.ArticleDto;
import com.example.mission.dto.CommentDto;
import com.example.mission.dto.PasswordDto;
import com.example.mission.repository.BoardRepository;
import com.example.mission.service.ArticleService;
import com.example.mission.service.BoardService;
import com.example.mission.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public String readOne(@PathVariable Long articleId, Model model) {
        model.addAttribute("article", articleService.readArticle(articleId));
        model.addAttribute("comments", commentService.readByArticleId(articleId));
        model.addAttribute("category", articleService.readArticle(articleId).getBoard().getCategory());
        model.addAttribute("boardId", articleService.readArticle(articleId).getBoard().getId());
        return "article";
    }

    @PostMapping("/{articleId}/comment")
    public String commentWrite(@PathVariable Long articleId, CommentDto commentDto) {
        commentService.create(commentDto, articleId);
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String delete(@PathVariable Long articleId, ArticleDto articleDto) {
        Long boardId = articleService.delete(articleDto, articleId).getBoard().getId();
        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/{articleId}/password-check")
    public ResponseEntity<Boolean> passwordCheck(@PathVariable Long articleId, @RequestBody PasswordDto passwordDto) {
        boolean isBoolean = articleService.passwordCheck(articleId, passwordDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(isBoolean);
    }
}
