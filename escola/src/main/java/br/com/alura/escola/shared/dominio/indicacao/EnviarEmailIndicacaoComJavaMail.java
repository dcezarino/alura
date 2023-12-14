package br.com.alura.escola.shared.dominio.indicacao;

import br.com.alura.escola.academico.aplicacao.indicacao.EnviarEmailIndicacao;
import br.com.alura.escola.academico.dominio.aluno.Aluno;

public class EnviarEmailIndicacaoComJavaMail implements EnviarEmailIndicacao{

	@Override
	public void enviarPara(Aluno indicado) {
		// Logica de envio de email com a lib Java Mail.
	}

}