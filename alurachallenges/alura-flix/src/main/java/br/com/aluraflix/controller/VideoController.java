package br.com.aluraflix.controller;

import br.com.aluraflix.dto.VideoDto;
import br.com.aluraflix.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDto>> listar() {
        List<VideoDto> videos = videoService.listar();
        return ResponseEntity.ok(videos);
    }

}
