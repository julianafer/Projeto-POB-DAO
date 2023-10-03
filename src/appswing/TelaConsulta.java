package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectContainer;

import modelo.Registro;
import modelo.Veiculo;
import regras_negocio.Fachada;
import javax.swing.JTextField;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_4;

	private ObjectContainer manager;
	private JComboBox comboBox;
	private JTextField textField;
	private JLabel label_1;
	private JTable table_1;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta tela = new TelaConsulta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Consulta");
		frame.setBounds(100, 100, 729, 359);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 55, 318, 198);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 289, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 264, 431, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index<0)
					label_4.setText("consulta nao selecionada");
				else
					switch(index) {
					case 0: 
						List<Veiculo> resultado1 = Fachada.VeiculosNRegistros(2);
						listagemVeiculo(resultado1);
						break;
					case 1: 
						String modelo = JOptionPane.showInputDialog("digite o modelo");
						List<Veiculo> resultado2 = Fachada.VeiculosNRegistros(5);
						listagemVeiculo(resultado2);
						break;
					case 2: 
						String n = JOptionPane.showInputDialog("digite N");
						int numero = Integer.parseInt(n);
						List<Veiculo> resultado3 = Fachada.VeiculosNRegistros(10);
						listagemVeiculo(resultado3);
						break;

					}

			}
		});
		button.setBounds(250, 21, 89, 23);
		frame.getContentPane().add(button);

		comboBox = new JComboBox();
		comboBox.setToolTipText("selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Possuem 2 ou mais registros", "Possuem 5 ou mais registros", "Possuem 10 ou mais registros"}));
		comboBox.setBounds(365, 22, 189, 22);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(52, 24, 169, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("Data:");
		label_1.setBounds(21, 26, 46, 14);
		frame.getContentPane().add(label_1);
		
		table_1 = new JTable();
		table_1.setShowGrid(true);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowSelectionAllowed(true);
		table_1.setRequestFocusEnabled(false);
		table_1.setGridColor(Color.BLACK);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setFocusable(false);
		table_1.setFillsViewportHeight(true);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(Color.LIGHT_GRAY);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.setBounds(365, 56, 316, 196);
		frame.getContentPane().add(table_1);
		
		button_1 = new JButton("Consultar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(592, 21, 89, 23);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					
					
					String data = textField.getText();

					Fachada.VeiculosEmDatas(data);
					listagemRegistros(Fachada.registrosData(data));
					listagemVeiculo(Fachada.VeiculosEmDatas(data));
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
	}

	public void listagemRegistros(List<Registro> lista) {
		try{
			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("Todos os registros");
	

			//adicionar linhas no model
			for(Registro reg : lista) {
				model.addRow(new Object[]{reg.getId(), reg.getVeiculo(), reg.getOperacao()});
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemVeiculo(List<Veiculo> lista) {
		try{
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("Todos os veiculos");

			//adicionar linhas no model
			for(Veiculo vei : lista) {
				model.addRow(new Object[]{vei.getPlaca(), vei.getTipoveiculo()} );
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
