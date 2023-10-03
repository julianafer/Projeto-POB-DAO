package appswing;

import java.util.List;

import javax.swing.JOptionPane;

import modelo.Veiculo;
import regras_negocio.Fachada;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data2 = JOptionPane.showInputDialog("digite a data (dd/mm/aaaa)");
		List<Veiculo> resultado2 = Fachada.veiculosEmDatas(data2);
		System.out.println(resultado2);
	}

}
