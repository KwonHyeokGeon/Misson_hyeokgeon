package com.example.mission.controller;

import com.example.mission.dto.ArticleDto;
import com.example.mission.dto.CommentDto;
import com.example.mission.dto.CommentPasswordDto;
import com.example.mission.dto.PasswordDto;
import com.example.mission.entity.Article;

import com.example.mission.service.ArticleService;
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

    // 단일 게시글 조회
    @GetMapping("/{articleId}")
    public String readOne(@PathVariable Long articleId, Model model) {
        model.addAttribute("article", articleService.readArticle(articleId));
        model.addAttribute("comments", commentService.readByArticleId(articleId));
        model.addAttribute("category", articleService.readArticle(articleId).getBoard().getCategory());
        model.addAttribute("boardId", articleService.readArticle(articleId).getBoard().getId());
        return "article";
    }

    // 댓글 작성
    @PostMapping("/{articleId}/comment")
    public String commentWrite(@PathVariable Long articleId, CommentDto commentDto) {
        commentService.create(commentDto, articleId);
        return "redirect:/articles/" + articleId;
    }

    // 게시글 업데이트
    @GetMapping("/{articleId}/update")
    public String updatePage(@PathVariable Long articleId, Model model) {
        Article article = articleService.readArticle(articleId);
        model.addAttribute("article", article);
        return "articleUpdate";
    }

    @PostMapping("/{articleId}/update")
    public String update(@PathVariable Long articleId, ArticleDto articleDto) {
        Long update = articleService.update(articleDto, articleId);
        return "redirect:/articles/" + update;
    }

    // 게시글 삭제
    @PostMapping("/{articleId}/delete")
    public String delete(@PathVariable Long articleId, ArticleDto articleDto) {
        Long boardId = articleService.delete(articleDto, articleId).getBoard().getId();
        return "redirect:/boards/" + boardId;
    }

    // 삭제하기 전 패스워드 일치여부 확인
    // article password
    @PostMapping("/{articleId}/password-check")
    public ResponseEntity<Boolean> passwordCheck(@PathVariable Long articleId, @RequestBody PasswordDto passwordDto) {
        boolean isBoolean = articleService.passwordCheck(articleId, passwordDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(isBoolean);
    }

    // commment password
    @PostMapping("/{articleId}/comment-password-check")
    public ResponseEntity<Boolean> commentPasswordCheck(@RequestBody CommentPasswordDto commentPasswordDto) {
        boolean isBoolean = commentService.passwordCheck(commentPasswordDto.getCommentId(), commentPasswordDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(isBoolean);
    }

    // 댓글 삭제
    @PostMapping("/{articleId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId, @PathVariable Long articleId, CommentDto commentDto) {
        commentService.delete(commentDto, commentId);
        return "redirect:/articles/" + articleId;
    }
}
