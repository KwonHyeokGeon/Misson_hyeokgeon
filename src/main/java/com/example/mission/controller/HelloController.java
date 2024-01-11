package com.example.mission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // 경로 미입력 시 게시판 페이지로 이동
    @GetMapping
    public String main() {
        return "redirect:/boards";
    }
}
