package com.example.mission.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/multipart")
public class MultiController {
    // consumes : 메소드가 수용할 수 있는 미디어 유형을 지정
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void multipart(@RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        log.info(multipartFile.getOriginalFilename());
        log.info(multipartFile.getName());

        String filename = multipartFile.getOriginalFilename();
        Files.createDirectories(Path.of("media"));
        Path path = Path.of("media/"+filename);
        multipartFile.transferTo(path);

    }
}