package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelo.Cliente;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servico.ClienteService;
import excecao.ClienteNaoEncontradoException;
import java.awt.Label;

public class DialogConta extends JDialog implements ActionListener 
{
	private static final long serialVersionUID = 1L;

	private static ContaService contaService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	clienteService = (ClienteService)fabrica.getBean ("clienteService");
    }
	
	private JButton buscarClienteButton;
	private JButton cadastrarButton;
	private JButton buscarButton;
	private JButton alterarButton;
	private JButton removerButton;
	
	private JTextField numeroTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel nomeMensagem;
	private JLabel sexoMensagem;
	private JLabel idadeMensagem;

	private JPanel panel;
	
	private Cliente umCliente;
	private JTextField dataAberturaTextField;
	private JTextField clienteTextField;
	
	public void designaClienteAFrame(Cliente umCliente)
	{
		this.umCliente = umCliente;
		
		numeroTextField.setText(umCliente.getNome());
		
		
		nomeMensagem.setText("");
		sexoMensagem.setText("");
		idadeMensagem.setText("");
	}
	
	public DialogConta(JFrame frame)
	{
		super(frame);
		
		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 0, 602, 292);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel cadastrarContasLabel = new JLabel("Cadastro de Contas");
		cadastrarContasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarContasLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarContasLabel.setBounds(189, 21, 190, 26);
		panel.add(cadastrarContasLabel);
		
		JLabel numeroLabel = new JLabel("Numero");
		numeroLabel.setBounds(89, 85, 47, 14);
		panel.add(numeroLabel);
		
		numeroTextField = new JTextField();
		numeroLabel.setLabelFor(numeroTextField);
		numeroTextField.setBounds(138, 82, 278, 20);
		panel.add(numeroTextField);
		numeroTextField.setColumns(50);
		numeroTextField.setBackground(SystemColor.window);
		
		JLabel dataAberturaLabel = new JLabel("Data de Abertura");
		dataAberturaLabel.setBounds(38, 113, 96, 20);
		panel.add(dataAberturaLabel);
		
		dataAberturaTextField = new JTextField();
		dataAberturaLabel.setLabelFor(dataAberturaTextField);
		dataAberturaTextField.setBounds(138, 113, 278, 20);
		panel.add(dataAberturaTextField);
		dataAberturaTextField.setColumns(10);
		dataAberturaTextField.setBackground(SystemColor.window);
		
		nomeMensagem = new JLabel("");
		nomeMensagem.setForeground(Color.RED);
		nomeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nomeMensagem.setBounds(138, 92, 241, 14);
		panel.add(nomeMensagem);
		
		
		buscarClienteButton = new JButton("Buscar Cliente");
		buscarClienteButton.setBounds(451, 218, 109, 23);
		panel.add(buscarClienteButton);
		buscarClienteButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(451, 82, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(451, 184, 96, 23);
		panel.add(buscarButton);
		buscarButton.addActionListener(this);
		
		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(451, 116, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(451, 150, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);
		
		JLabel clienteLabel = new JLabel("Cliente");
		clienteLabel.setBounds(89, 145, 39, 20);
		panel.add(clienteLabel);
		
		clienteTextField = new JTextField();
		clienteLabel.setLabelFor(clienteTextField);
		clienteTextField.setColumns(10);
		clienteTextField.setBackground(Color.WHITE);
		clienteTextField.setBounds(138, 144, 278, 20);
		panel.add(clienteTextField);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
	private boolean validaCliente()
	{
		boolean deuErro = false;
		if (numeroTextField.getText().trim().length() == 0)
		{	deuErro = true;
			nomeMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	nomeMensagem.setText("");
		}
		
		
		
		return deuErro;
	}
	
	public void novo()
	{
		numeroTextField.setEnabled(true);
	
		
		numeroTextField.setText("");
		buttonGroup.clearSelection();
		

		buscarClienteButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		buscarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		
		buscarButton.setEnabled(true);
	}

	public void salvo()
	{
		numeroTextField.setEnabled(false);
	

		buscarClienteButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		buscarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		
		buscarButton.setEnabled(true);
	}

	public void editavel()
	{
		numeroTextField.setEnabled(true);
	

		buscarClienteButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		buscarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		
		buscarButton.setEnabled(false);
	}

	public void removido()
	{
		numeroTextField.setEnabled(false);
		

		buscarClienteButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		buscarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado()
	{
		numeroTextField.setEnabled(false);
		

		buscarClienteButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		buscarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		buscarButton.setEnabled(true);
	}
}
