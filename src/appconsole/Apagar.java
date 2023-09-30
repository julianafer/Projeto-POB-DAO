package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Registro;
import modelo.Veiculo;

public class Apagar {
	protected ObjectContainer manager;

	public Apagar() {
		try {
			manager = Util.conectarBanco();
			System.out.println("excluindo");
			
			//localizar o veiculo KFC2002 no banco
			Query q = manager.query();
			q.constrain(Veiculo.class);  				
			q.descend("placa").constrain("KFC2002");		 
			List<Veiculo> resultados = q.execute();

			if(resultados.size()>0) {
				Veiculo veiculo = resultados.get(0);

				// apagar cada registro (orfao)
				for (Registro r : veiculo.getRegistros()) {
					manager.delete(r);  //apagar registro para nao ficar orfao
				}

				// apagar o veiculo
				manager.delete(veiculo);
				manager.commit();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Apagar();
	}
}
