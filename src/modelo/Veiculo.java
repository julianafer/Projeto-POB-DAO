package modelo;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {
	
	private String placa;
	private TipoVeiculo tipoveiculo; // carro, moto...
	private List<Registro> registros = new ArrayList<>(); // iserir, remover e localizar
	
	public Veiculo(String placa, TipoVeiculo tipoveiculo) {
		super();
		this.placa = placa;
		this.tipoveiculo = tipoveiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getTipoveiculo() {
		return tipoveiculo;
	}

	public void setTipoveiculo(TipoVeiculo tipoveiculo) {
		this.tipoveiculo = tipoveiculo;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	public void inserirRegistro(Registro registro) {
		registros.add(registro);
	}
	
	public void removerRegistro(Registro registro) {
		registros.remove(registro);
	}
	
	public Registro localizarRegistro(int id) {
		for (Registro r : registros) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Veiculo: placa=" + placa + ", tipoveiculo=" + tipoveiculo.getNome() ;
	}
	
}
