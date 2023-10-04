package daodb4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Registro;
import modelo.Veiculo;

public class DAORegistro  extends DAO<Registro>{

	public Registro read (Object chave){
		int id = (int) chave;	
		Query q = manager.query();
		q.constrain(Registro.class);
		q.descend("id").constrain(id);
		List<Registro> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//metodo da classe DAO sobrescrito DAORegistro para
	//criar "id" sequencial 
	public void create(Registro obj){
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}
	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	// quais os registros da data X
	public List<Registro> registrosData(String data){
		Query q;
		q = manager.query();
		q.constrain(Registro.class);
		q.descend("datahora").constrain(data).contains();
		q.descend("id").orderAscending();
		return q.execute();
	}
	
	public List<Veiculo> registrosDataVeiculo(String data){
		Query q = manager.query();
		q = manager.query();
		q.constrain(Registro.class);
		q.descend("datahora").constrain(data).contains();
		q.descend("id").orderAscending();
		List<Registro> resultado = q.execute();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		for (Registro registro : resultado) {
			veiculos.add(registro.getVeiculo());
		}
		return veiculos;
	}
	
	public List<Veiculo> veiculosN(int n){
		Query q = manager.query();
		q.constrain(Registro.class);
		List<Registro> resultado = q.execute();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		List<Veiculo> veiculosAux = new ArrayList<Veiculo>();
		for (Registro registro : resultado) {
			veiculos.add(registro.getVeiculo());
		}
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getRegistros().size() > n) {
				veiculosAux.add(veiculo);
			}
		}
		return veiculosAux;
	}
	
}
