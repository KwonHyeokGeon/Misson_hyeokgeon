package com.example.mission.dto;


import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;

    public CommentDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public static CommentDto from(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.id = comment.getId();
        commentDto.title = comment.getTitle();
        commentDto.content = comment.getContent();
        commentDto.password = comment.getPassword();
        return commentDto;
    }
}
