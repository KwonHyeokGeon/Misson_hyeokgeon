package com.example.mission.service;

import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Board;
import com.example.mission.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시판 목록 가져오기
    public List<BoardDto> readAll() {
        List<BoardDto> boardDto = new ArrayList<>();
        List<Board> boards = boardRepository.findAll();
        for (Board board : boards) {
            boardDto.add(BoardDto.from(board));
        }
        return boardDto;
    }

    public BoardDto read(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다."));
        return BoardDto.from(board);
    }
}
