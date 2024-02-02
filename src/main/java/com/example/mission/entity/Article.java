package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;
    @Setter
    private LocalDateTime writtenDate;


    @Setter
    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

}
