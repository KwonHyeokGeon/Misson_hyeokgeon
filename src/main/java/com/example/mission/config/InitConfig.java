package com.example.mission.config;

import com.example.mission.entity.Board;
import com.example.mission.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitConfig {
    private final BoardRepository boardRepository;

    @PostConstruct
    public void initBoard() {
        List<Board> boards = List.of(
                new Board("자유 게시판"),
                new Board("개발 게시판"),
                new Board("일상 게시판"),
                new Board("사건사고 게시판")
        );
        boardRepository.saveAll(boards);
    }


}

