package br.com.alura.escola.dominio.aluno;

public class FabricaDeAluno {
	
	private Aluno aluno;
	
	public FabricaDeAluno comNomeCPFEmail(String nome, String cpf, String email) {
		
		this.aluno = new Aluno(new CPF(cpf), nome, new Email(email));
		return this;
		
	}
	
	public FabricaDeAluno comTelefone(String ddd, String numero ) throws TelefoneMaxException {
		
		this.aluno.adicionarTelefone(ddd, numero);
		return this;
		
	}
	
	public Aluno criar() {
		
		return this.aluno;	
		
	}
	
	public static void main(String[] args) throws TelefoneMaxException {
		
		FabricaDeAluno fabrica = new FabricaDeAluno();
		Aluno aluno = fabrica.comNomeCPFEmail("Aluno Modelo", "00000000000", "alunomodelo@teste.com.br")
				             .comTelefone("19", "991334488")
				             .criar();
		
		System.out.println(aluno.toString());
		
	}

}