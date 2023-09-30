package modelo;

public class TipoVeiculo {

	private String nome;

	public TipoVeiculo(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "TipoVeiculo: nome=" + nome;
	}
	
}
