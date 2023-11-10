package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "adocoes")
public class Adocao {

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
        this.data = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    // Todo relacionamento ToOne deve ser Lazy
    // Por padrão quando carregar uma adoção o Tutor e o Pet não vem junto automático.
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;

    public void marcarComoAprovada() {

        this.status = StatusAdocao.APROVADO;

    }

    public void marcarComoReprovada(String justificativa) {

        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;

    }
}
