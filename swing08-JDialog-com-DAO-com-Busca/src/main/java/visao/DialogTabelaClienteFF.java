package visao;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class DialogTabelaClienteFF extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField nomeTextField;
	
	private JTable table;
	private ClienteModelFF clienteModel;
	private JScrollPane scrollPane;
	
	private TableColumnModel columnModel;
	
	DialogClienteFF dialogCliente;
	DialogConta dialogConta;
	
	public DialogTabelaClienteFF(DialogClienteFF dialogCliente)
	{
		super(dialogCliente);
		
		this.dialogCliente = dialogCliente;
		
		setTitle("Pesquisa de Clientes");
		setBounds(110, 171, 608, 301);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 588, 111);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPesquisaPorNome = new JLabel("Pesquisa por Nome");
		lblPesquisaPorNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPesquisaPorNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisaPorNome.setBounds(203, 11, 195, 22);
		panel.add(lblPesquisaPorNome);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(92, 42, 55, 22);
		panel.add(lblNome);
		
		nomeTextField = new JTextField();
		nomeTextField.setBackground(UIManager.getColor("Button.light"));
		nomeTextField.setForeground(SystemColor.desktop);
		nomeTextField.setBounds(142, 44, 324, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(249, 75, 102, 23);
		panel.add(btnPesquisar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 112, 588, 144);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		/**********************************************************************/

		columnModel = table.getColumnModel();
	}
	
	
	public DialogTabelaClienteFF(DialogConta dialogConta)
	{
		super(dialogConta);
		
		this.dialogConta = dialogConta;
		
		setTitle("Pesquisa de Clientes");
		setBounds(110, 171, 608, 301);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 588, 111);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPesquisaPorNome = new JLabel("Pesquisa por Nome");
		lblPesquisaPorNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPesquisaPorNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisaPorNome.setBounds(203, 11, 195, 22);
		panel.add(lblPesquisaPorNome);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(92, 42, 55, 22);
		panel.add(lblNome);
		
		nomeTextField = new JTextField();
		nomeTextField.setBackground(UIManager.getColor("Button.light"));
		nomeTextField.setForeground(SystemColor.desktop);
		nomeTextField.setBounds(142, 44, 324, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(249, 75, 102, 23);
		panel.add(btnPesquisar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 112, 588, 144);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		/**********************************************************************/

		columnModel = table.getColumnModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		clienteModel = new ClienteModelFF();	
		clienteModel.setNomeCliente(nomeTextField.getText());
		table.setModel(clienteModel);

		// Designa um renderer e um editor para os bot�es.
		// Para cada bot�o exibido � executado o m�todo getTableCellRendererComponent() 
		// que retorna o bot�o que deve ser renderizado.
		// E sempre que um bot�o � clicado � executado o m�todo getTableCellEditorComponent()
		// que retorna o bot�o que foi clicado para que o listener deste bot�o possa ser executado.
		//new ButtonColumn(table, 5, this, dialogCliente); //Parametros na ordem ==> Tabela, n�mero da coluna onde est� o bot�o, this da janela de busca, janela anterior a janela de busca.
		if(dialogConta == null){
			new ButtonColumn(table, 3, this, dialogCliente);
		}
		else if(dialogCliente == null){
			new ButtonColumn(table, 3, this, dialogConta);
		}
		// Designa um valor preferido para a coluna. Se ele for menor
		// ou maior do que o m�ximo poss�vel, ele ser� ajustado.
		// columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(30);
		columnModel.getColumn(3).setPreferredWidth(30);
		
		scrollPane.setVisible(true);
	}
}
