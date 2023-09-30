package modelo;

import appconsole.Util;

public class Registro {

	private int id; // gerar id na classe util do exemplo locadora (eu acho)
	private String datahora;
	private Veiculo veiculo;
	private String operacao; // entrada ou saida
	
	public Registro(String datahora, Veiculo veiculo, String operacao) {
		super();
		this.setId(Util.gerarIdRegistro());
		this.datahora = datahora;
		this.veiculo = veiculo;
		this.operacao = operacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatahora() {
		return datahora;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	@Override
	public String toString() {
		return "Registro: id=" + id + ", datahora=" + datahora + ", veiculo=" + veiculo.getPlaca() + ", operacao=" + operacao;
	}
}
