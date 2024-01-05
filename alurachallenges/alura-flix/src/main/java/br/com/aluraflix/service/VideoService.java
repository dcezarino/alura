package br.com.aluraflix.service;

import br.com.aluraflix.dto.VideoDto;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
