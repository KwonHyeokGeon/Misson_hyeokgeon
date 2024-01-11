package com.example.mission.service;

import com.example.mission.dto.CommentDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import com.example.mission.repository.ArticleRepository;
import com.example.mission.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public Long create(CommentDto commentDto, Long articleId) {
        Comment comment = new Comment();
        comment.setTitle(commentDto.getTitle());
        comment.setContent(commentDto.getContent());
        comment.setPassword(commentDto.getPassword());
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        comment.setArticle(article);
        commentRepository.save(comment);
        return article.getId();
    }

    // 해당 게시글에 포함된 댓글 읽기
    public List<Comment> readByArticleId(Long articleId) {
        return commentRepository.findCommentByArticleId(articleId);
    }

    @Transactional
    public void delete(CommentDto commentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글없다."));
        if (commentDto.getPassword().equals(comment.getPassword()))
            commentRepository.delete(comment);
    }

    public boolean passwordCheck(Long commentId, String password) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        return comment.getPassword().equals(password);
    }
}
