package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table(name = "abrigos")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    public Abrigo(CadastroAbrigoDto dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

}
