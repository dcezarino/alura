package br.com.alura.screenmatch.modelos;

public class Titulo implements Comparable<Titulo>{

	private String nome;
	private int anoDeLancamento;
	private boolean incluidoNoPlano;
	private double somaDasAvaliacoes;
	private int totalDeAvaliacoes;
	private int duracaoEmMinutos;
	
	public Titulo(String nome, int anoDeLancamento) {		
		this.nome = nome;
		this.anoDeLancamento = anoDeLancamento;
	}
		
	public void exibeFichaTecnica() {
		
		System.out.println("Filme [nome=" + nome + ", "
							+ "anoDeLancamento=" + anoDeLancamento + ", "
							+ "incluidoNoPlano=" + incluidoNoPlano
							+ ", avaliacao=" + somaDasAvaliacoes + ", "
							+ "totalAvaliacoes=" + totalDeAvaliacoes + ", "
							+ "duracaoEmMinutos=" + duracaoEmMinutos + "]");
		
	}
	
	public void avalia (double nota) {
		
		this.somaDasAvaliacoes += nota;
		this.totalDeAvaliacoes++;
		
	}
	
	public double retornaMedia() {
		
		return (this.somaDasAvaliacoes / this.totalDeAvaliacoes);
		
	}
	
	public int getTotalDeAvaliacoes() {
		return totalDeAvaliacoes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAnoDeLancamento(int anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public void setIncluidoNoPlano(boolean incluidoNoPlano) {
		this.incluidoNoPlano = incluidoNoPlano;
	}

	public void setDuracaoEmMinutos(int duracaoEmMinutos) {
		this.duracaoEmMinutos = duracaoEmMinutos;
	}

	public int getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}

	public String getNome() {
		return nome;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public boolean isIncluidoNoPlano() {
		return incluidoNoPlano;
	}

	public void setTotalDeAvaliacoes(int totalDeAvaliacoes) {
		this.totalDeAvaliacoes = totalDeAvaliacoes;
	}

	@Override
	public int compareTo(Titulo outroTitulo) {
		return this.getNome().compareTo(outroTitulo.getNome());
	}

}