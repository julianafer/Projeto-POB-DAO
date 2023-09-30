package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Veiculo;
import modelo.Registro;

public class Alterar {
	protected ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		atualizar();
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}
 
	public void atualizar(){
		//localizar veiculo com placa AAB1001
		Query q = manager.query();
		q.constrain(Veiculo.class);  				
		q.descend("placa").constrain("AAB1001");		 
		List<Veiculo> resultados = q.execute(); // select v from Veiculo v where p.placa="AAB1001"
		
		if(resultados.size()>0) {
			Veiculo v =  resultados.get(0);
			v.setPlaca("ABC1234");
			
			//adicionar novo registro
			v.inserirRegistro(new Registro("13/02/2023 11:27",  v , "entrada"));

			manager.store(v);
			manager.commit();
			System.out.println("alterou placa e registros do veiculo");
		}
		else
			System.out.println("veiculo AAB1001 inexistente");
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}

