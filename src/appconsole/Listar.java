package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Registro;
import modelo.TipoVeiculo;
import modelo.Veiculo;

public class Listar {
	protected ObjectContainer manager;

	public Listar() {
		try {
			manager = Util.conectarBanco();
			
			System.out.println("\n---listagem de tipos de veículos:");
			Query q = manager.query();
			q.constrain(TipoVeiculo.class);  				
			List<TipoVeiculo> resultados1 = q.execute();
			for(TipoVeiculo t: resultados1)
				System.out.println(t);

			System.out.println("\n---listagem de veículos:");
			q = manager.query();
			q.constrain(Veiculo.class);  				
			List<Veiculo> resultados2 = q.execute();
			for(Veiculo v: resultados2)
				System.out.println(v);
			
			System.out.println("\n---listagem de registros:");
			q = manager.query();
			q.constrain(Registro.class);  				
			List<Registro> resultados3 = q.execute();
			for(Registro r: resultados3)
				System.out.println(r);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
