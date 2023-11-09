package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private String nome;

    private String raca;

    private Integer idade;

    private String cor;

    private Float peso;

    private Boolean adotado;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adocao adocao;

    public Pet(Abrigo abrigo, CadastroPetDto dto) {

        this.tipo = dto.tipo();
        this.nome = dto.nome();
        this.raca = dto.raca();
        this.idade = dto.idade();
        this.cor = dto.cor();
        this.peso = dto.peso();
        this.adotado = dto.adotado();
        this.abrigo = abrigo;

    }

}
