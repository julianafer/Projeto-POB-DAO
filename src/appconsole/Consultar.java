package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Registro;
import modelo.Veiculo;

public class Consultar {
	protected ObjectContainer manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();
			
			
			System.out.println("consultas... \n");
			
			System.out.println("\nQuais os registros da data 11/02/2023");
			Query q = manager.query();
			q.constrain(Registro.class);
			q.descend("datahora").constrain("11/02/2023").contains();  //condição
			q.descend("id").orderAscending();	//ordenação
			List<Registro> resultados = q.execute();
			for(Registro r : resultados)
				System.out.println(r);
			
		
			System.out.println("\nQuais os veiculos contendo registro na data 12/02/2023");
			q = manager.query();
			q.constrain(Veiculo.class);
			q.descend("registros").descend("datahora").constrain("12/02/2023").contains();  //condição
			q.descend("placa").orderAscending();	//ordenação
			List<Veiculo> resultados2 = q.execute();
			for(Veiculo v : resultados2)
				System.out.println(v);
			
			
			System.out.println("\nQuais os veículos contendo mais de 1 registro");
			q = manager.query();
			q.constrain(Veiculo.class);
			q.constrain( new Filtro1() );
			List<Veiculo> resultados3 = q.execute();
			for(Veiculo v : resultados3)
				System.out.println(v);
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

//classe interna
class Filtro1 implements Evaluation {
	public void evaluate(Candidate candidate) {
		Veiculo veiculo = (Veiculo) candidate.getObject();
		if(veiculo.getRegistros().size() > 1) 
			candidate.include(true); 
		else
			candidate.include(false);
	}
}
