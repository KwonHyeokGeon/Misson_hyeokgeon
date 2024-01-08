package com.example.mission.service;

import com.example.mission.entity.Board;
import com.example.mission.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시판 목록 가져오기
    public List<Board> readAll() {
        return boardRepository.findAll();
    }
}
