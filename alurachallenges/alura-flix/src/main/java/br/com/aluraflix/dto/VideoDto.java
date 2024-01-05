package br.com.aluraflix.dto;

import br.com.aluraflix.model.Video;

public record VideoDto(Long id, String titulo, String nome, String url) {

    public VideoDto(Video video) {
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl());
    }

}
