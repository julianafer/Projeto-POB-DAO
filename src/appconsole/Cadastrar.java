package appconsole;

import com.db4o.ObjectContainer;

import modelo.Registro;
import modelo.Veiculo;
import modelo.TipoVeiculo;

public class Cadastrar {
	protected ObjectContainer manager;

	public Cadastrar() {
		try {
			manager = Util.conectarBanco();

			System.out.println("cadastrando...");
			
			TipoVeiculo carro = new TipoVeiculo("carro");
			manager.store(carro);
			manager.commit();
			
			TipoVeiculo moto = new TipoVeiculo("moto");
			manager.store(moto);
			manager.commit();
			
			Veiculo veiculo1 = new Veiculo("AAA1000", moto);
			manager.store(veiculo1);
			manager.commit();
			
			Veiculo veiculo2 = new Veiculo("AAB1001", carro);
			manager.store(veiculo2);
			manager.commit();
			
			Veiculo veiculo3 = new Veiculo("BCD1012", carro);
			manager.store(veiculo3);
			manager.commit();
			
			Veiculo veiculo4 = new Veiculo("KFC2002", carro);
			manager.store(veiculo4);
			manager.commit();
			
			Registro registro1 = new Registro("11/02/2023 11:55",  veiculo1 , "entrada");
			manager.store(registro1);
			manager.commit();
			
			Registro registro2 = new Registro("11/02/2023 11:58",  veiculo3 , "entrada");
			manager.store(registro2);
			manager.commit();
			
			Registro registro3 = new Registro("11/02/2023 12:37",  veiculo1 , "saida");
			manager.store(registro3);
			manager.commit();
			
			Registro registro4 = new Registro("12/02/2023 15:20",  veiculo2 , "entrada");
			manager.store(registro4);
			manager.commit();
			
			Registro registro5 = new Registro("12/02/2023 18:50",  veiculo2 , "saida");
			manager.store(registro5);
			manager.commit();
			
			Registro registro6 = new Registro("13/02/2023 14:00",  veiculo4 , "entrada");
			manager.store(registro6);
			manager.commit();
			
			veiculo1.inserirRegistro(registro1);
			veiculo1.inserirRegistro(registro3);
			manager.store(veiculo1);
			manager.commit();
			
			veiculo2.inserirRegistro(registro4);
			veiculo2.inserirRegistro(registro5);
			manager.store(veiculo2);
			manager.commit();
			
			veiculo3.inserirRegistro(registro2);
			manager.store(veiculo3);
			manager.commit();
			
			veiculo4.inserirRegistro(registro6);
			manager.store(veiculo4);
			manager.commit();
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
