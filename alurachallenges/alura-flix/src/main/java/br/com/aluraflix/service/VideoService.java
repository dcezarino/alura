package br.com.aluraflix.service;

import br.com.aluraflix.dto.CadastroVideoDto;
import br.com.aluraflix.dto.VideoDto;
import br.com.aluraflix.exception.ValidacaoException;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<VideoDto> listar() {
        return videoRepository
                .findAll()
                .stream()
                .map(VideoDto::new)
                .toList();
    }

    public Video cadastrar(CadastroVideoDto dto) {
        boolean jaCadastrado = videoRepository.existsByTituloOrDescricaoOrUrl(dto.titulo(), dto.descricao(), dto.url());
        if(jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro video!");
        }
        return videoRepository.save(new Video(dto));
    }

    public List<VideoDto> listarVideoDto(Long id) {
         Optional<Video> videoOptional = videoRepository.findById(id);
         if (videoOptional.isPresent()) {
             return List.of(new VideoDto(videoOptional.get()));
         } else {
             throw new ValidacaoException("Não encontrado.");
         }
    }

}
