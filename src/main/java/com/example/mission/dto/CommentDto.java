package com.example.mission.dto;


import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String title;
    private String content;
    private String password;

    public static CommentDto from(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.title = comment.getTitle();
        commentDto.content = comment.getContent();
        commentDto.password = comment.getPassword();
        return commentDto;
    }
}
