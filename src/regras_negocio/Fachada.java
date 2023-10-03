package regras_negocio;

import daodb4o.DAOVeiculo;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAORegistro;
import daodb4o.DAOTipoVeiculo;
import modelo.Veiculo;
import modelo.Registro;
import modelo.TipoVeiculo;

public class Fachada {
	
	private static DAOVeiculo daoveiculo = new DAOVeiculo();  
	private static DAORegistro daoregistro = new DAORegistro(); 
	private static DAOTipoVeiculo daotipoveiculo = new DAOTipoVeiculo();
	
	public static void inicializar() {
		DAO.open();
	}
	
	public static void finalizar() {
		DAO.close();
	}
	
	// ---- criar
	
	public static TipoVeiculo criarTipo(String nome) throws Exception {
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo!=null)
			throw new Exception("Tipo de veiculo ja cadastrado: " + nome);
		tipo = new TipoVeiculo(nome);

		daotipoveiculo.create(tipo);
		DAO.commit();
		return tipo;
	}
	
	public static Veiculo criarVeiculo(String placa, String tipoVeiculo) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo!=null)
			throw new Exception("Veiculo ja cadastrado: " + placa);
		
		TipoVeiculo tipo = daotipoveiculo.read(tipoVeiculo);
		if (tipo==null)
			throw new Exception("tipo " + tipoVeiculo + " inexistente");
		veiculo = new Veiculo(placa, tipo);

		daoveiculo.create(veiculo);
		DAO.commit();
		return veiculo;
	}
	
	public static Registro criarRegistro(String datahora, String placa, String operacao) throws Exception {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("veiculo " + placa + " inexistente");
		
		List<Registro> registros = veiculo.getRegistros();
		
		/*if (operacao.equals("entrada")) {
			if (registros.get(registros.size()-1).getOperacao().equals("entrada")) 
				throw new Exception("Entrada já registrada");
		}
		else {
			if (registros.get(registros.size()-1).getOperacao().equals("saida")) 
				throw new Exception("Saida já registrada");
		}*/

		Registro registro = new Registro(datahora, veiculo, operacao);
		daoregistro.create(registro);
		DAO.commit();
		return registro;
	}
	
	// ---- alterar
	
	public static void alterarTipo(String nome, String novonome) throws Exception{
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null) {
			DAO.rollback();
			throw new Exception("O tipo não existe");
		}
		tipo.setNome(novonome);
		daotipoveiculo.update(tipo);
		DAO.commit();	
	}
	
	public static void alterarVeiculo(String placa, String novaplaca) throws Exception  {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null) {
			DAO.rollback();
			throw new Exception("O veiculo não existe");
		}
		veiculo.setPlaca(novaplaca);
		daoveiculo.update(veiculo);
		DAO.commit();
	}
	
	// ---- excluir

	public static void excluirTipo(String nome) throws Exception {
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if(tipo==null) 
			throw new Exception ("Tipo " + nome + " incorreto para exclusao");
		
		// excluir os veiculos cadastrados com esse tipo
		for (Veiculo v : listarVeiculos()) {
			if (v.getTipoveiculo().equals(tipo))
				daoveiculo.delete(v);
		}

		// apagar o tipo
		daotipoveiculo.delete(tipo);
		DAO.commit();
	}
	
	public static void excluirVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo " + placa + " não cadastrado");

		daoveiculo.delete(veiculo);
		DAO.commit();
	}
	
	public static void excluirRegistro(int id) throws Exception{
		DAO.begin();
		Registro registro = daoregistro.read(id);
		if (registro==null)
			throw new Exception("Registro " + id + " não cadastrado");

		daoregistro.delete(registro);
		DAO.commit();
	}
	
	// ---- localizar
	
	public static TipoVeiculo localizarTipo(String nome) throws Exception{
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null)
			throw new Exception("Tipo: " + nome + " inexistente");
		return tipo;
	}
	
	public static Veiculo localizarVeiculo(String placa) throws Exception{
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo da placa: " + placa + " não existe");
		return veiculo;
	};

	public static Registro localizarRegistro(int id) throws Exception{
		Registro registro = daoregistro.read(id);
		if (registro==null)
			throw new Exception("ID: " + id + " não existe");
		
		return registro;
	}

	// ---- listar
	
	public static List<TipoVeiculo> listarTipos(){
		DAO.begin();
		List<TipoVeiculo> resultados =  daotipoveiculo.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Veiculo> listarVeiculos(){
		DAO.begin();
		List<Veiculo> resultados =  daoveiculo.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Registro> listarRegistros(){
		DAO.begin();
		List<Registro> resultados =  daoregistro.readAll();
		DAO.commit();
		return resultados;
	}
	
	// ---- Consultas
	
	public static List<Veiculo> veiculosEmDatas(String data){
		DAO.begin();
		List<Veiculo> resultado= daoveiculo.veiculosData(data);
		DAO.commit();
		return resultado;
	}
	
	public static List<Veiculo> veiculosNRegistros(int n){
		DAO.begin();
		List<Veiculo> resultado= daoveiculo.veiculosNRegistros(n);
		DAO.commit();
		return resultado;
	}
	
	public static List<Registro> registrosData(String data) {
		DAO.begin();
		List<Registro> resultado= daoregistro.registrosData(data);
		DAO.commit();
		return resultado;
	} 
	
}
