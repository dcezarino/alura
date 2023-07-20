package alura.jmilhas.api.domain.depoimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "depoimentos")
@Entity(name = "Depoimento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Depoimento {
	
	public Depoimento(@Valid DadosCadastroDepoimento request) {
		this.foto = request.foto();
		this.descricao = request.descricao();
		this.nomeResponsavel = request.nomeResponsavel();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String foto;
	
	private String descricao;
	
	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

}