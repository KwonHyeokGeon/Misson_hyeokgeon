package com.example.mission.config;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.repository.ArticleRepository;
import com.example.mission.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitConfig {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    @PostConstruct
    public void initBoard() {
        List<Board> boards = List.of(
                new Board("자유 게시판"),
                new Board("개발 게시판"),
                new Board("일상 게시판"),
                new Board("사건사고 게시판")
        );
        boardRepository.saveAll(boards);

        List<Article> articles = new ArrayList<>();

        // pagination 용 더미 데이터
        for (int i = 1; i < 20; i++) {
            Article article = Article.builder().title("자유게시판 글 제목 " + i)
                    .content("자유게시판 글 내용 " + i)
                    .password("1111")
                    .writtenDate(LocalDateTime.now())
                    .board(boards.get(0))
                    .comments(new ArrayList<>())
                    .build();
            articles.add(article);
        }
        for (int i = 0; i < boards.size(); i++) {
            String title = generateTitle(i);
            String content = generateContent(i);

            Article article = Article.builder()
                    .title(title)
                    .content(content)
                    .password("1111")
                    .writtenDate(LocalDateTime.now())
                    .board(boards.get(i))
                    .comments(new ArrayList<>())
                    .build();

            articles.add(article);
        }

        articleRepository.saveAll(articles);

    }

    private String generateTitle(int index) {
        switch (index) {
            case 0:
                return "자유로운 이야기";
            case 1:
                return "개발 이야기";
            case 2:
                return "오늘의 일상";
            case 3:
                return "사건사고 소식";
            default:
                return "제목 없음";
        }
    }

    private String generateContent(int index) {
        switch (index) {
            case 0:
                return "자유롭게 이야기를 나누는 공간입니다.";
            case 1:
                return "최신 개발 트렌드와 경험을 공유하는 공간입니다.";
            case 2:
                return "일상에서 겪은 재미있는 이야기를 공유해보세요.";
            case 3:
                return "사건사고 소식을 공유하고 논의하는 공간입니다.";
            default:
                return "내용 없음";
        }
    }
}

