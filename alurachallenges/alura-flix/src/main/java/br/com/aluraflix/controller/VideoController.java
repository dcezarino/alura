package br.com.aluraflix.controller;

import br.com.aluraflix.dto.CadastroVideoDto;
import br.com.aluraflix.dto.VideoDto;
import br.com.aluraflix.exception.ValidacaoException;
import br.com.aluraflix.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    @Transactional
    public ResponseEntity<List<VideoDto>> listar() {
        List<VideoDto> videos = videoService.listar();
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroVideoDto dto) {
        try {
            return ResponseEntity.ok(videoService.cadastrar(dto));
        } catch (ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> listaVideoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(videoService.listarVideoDto(id));
        } catch (ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


}
