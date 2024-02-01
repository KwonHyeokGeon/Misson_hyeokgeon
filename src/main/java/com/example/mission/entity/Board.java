package com.example.mission.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String category;

    public Board(String category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "board")
    private List<Article> article;
}
