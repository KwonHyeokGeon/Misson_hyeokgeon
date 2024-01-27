package com.example.mission.dto;


import com.example.mission.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDto {
    private Long id;

    private String category;


    public static BoardDto from(Board board) {
        BoardDto dto = new BoardDto();
        dto.id = board.getId();
        dto.category = board.getCategory();

        return dto;
    }
}
