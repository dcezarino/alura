package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizaTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutoDto;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTutoDto dto) {

        try {
            tutorService.cadastrarTutor(dto);
            return ResponseEntity.ok().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizaTutorDto dto) {

       try {
           tutorService.atualizarTutor(dto);
           return ResponseEntity.ok().build();
       } catch (ValidationException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}
