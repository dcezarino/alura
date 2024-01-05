package br.com.aluraflix.model;


import br.com.aluraflix.dto.CadastroVideoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String descricao;

    private String url;

    public Video(CadastroVideoDto dto) {
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.url = dto.url();
    }

}