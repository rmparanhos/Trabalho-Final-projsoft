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

public class DialogClienteFF extends JDialog implements ActionListener 
{
	private static final long serialVersionUID = 1L;

	private static ClienteService clienteService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	clienteService = (ClienteService)fabrica.getBean ("clienteService");
    }
	
	private JButton novoButton;
	private JButton cadastrarButton;
	private JButton editarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton cancelarButton;
	private JButton buscarButton;
	
	private JTextField nomeTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel nomeMensagem;
	private JLabel sexoMensagem;
	private JLabel idadeMensagem;

	private JPanel panel;
	
	private Cliente umCliente;
	private JTextField dataNascTextField;
	
	public void designaClienteAFrame(Cliente umCliente)
	{
		this.umCliente = umCliente;
		
		nomeTextField.setText(umCliente.getNome());
		
		
		nomeMensagem.setText("");
		sexoMensagem.setText("");
		idadeMensagem.setText("");
	}
	
	public DialogClienteFF(JFrame frame)
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
		
		JLabel cadastrarLabel = new JLabel("Cadastro de Clientes");
		cadastrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarLabel.setBounds(189, 21, 190, 26);
		panel.add(cadastrarLabel);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(62, 78, 46, 14);
		panel.add(nomeLabel);
		
		nomeTextField = new JTextField();
		nomeLabel.setLabelFor(nomeTextField);
		nomeTextField.setBounds(138, 75, 278, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(50);
		nomeTextField.setBackground(SystemColor.window);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(10, 113, 173, 20);
		panel.add(lblDataNascimento);
		
		dataNascTextField = new JTextField();
		dataNascTextField.setBounds(20, 145, 241, 20);
		panel.add(dataNascTextField);
		dataNascTextField.setColumns(10);
		dataNascTextField.setBackground(SystemColor.window);
		
		nomeMensagem = new JLabel("");
		nomeMensagem.setForeground(Color.RED);
		nomeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nomeMensagem.setBounds(138, 92, 241, 14);
		panel.add(nomeMensagem);
		
		
		novoButton = new JButton("Novo");
		novoButton.setBounds(452, 50, 96, 23);
		panel.add(novoButton);
		novoButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(473, 112, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);
		
		editarButton = new JButton("Editar");
		editarButton.setBounds(426, 83, 96, 23);
		panel.add(editarButton);
		editarButton.addActionListener(this);
		
		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(413, 152, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(473, 184, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(378, 212, 96, 23);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(this);

		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(473, 246, 96, 23);
		panel.add(buscarButton);
		
		
		buscarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		if (obj == novoButton)
		{	novo();
		}
		else if (obj == cadastrarButton)
		{
			boolean deuErro = validaCliente();
			
			if(!deuErro)
			{	
				umCliente = new Cliente();
				umCliente.setNome(nomeTextField.getText().toUpperCase());
			

				clienteService.inclui(umCliente);
				
				salvo();
				
				JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(obj == editarButton)
		{
			editavel();
		}
		else if(obj == alterarButton)
		{
			boolean deuErro = validaCliente();
			
			if(!deuErro)
			{	
				umCliente.setNome(nomeTextField.getText().toUpperCase());
			

				try 
				{
					clienteService.altera(umCliente);
					
					salvo();
					
					JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso", "", 
							JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (ClienteNaoEncontradoException e1) 
				{
					novo();
					
					JOptionPane.showMessageDialog(this, "Cliente não encontrado", "", 
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(obj == removerButton)
		{
			try 
			{
				clienteService.exclui(umCliente);

				removido();
				
				JOptionPane.showMessageDialog(this, "Cliente removido com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (ClienteNaoEncontradoException e1) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Cliente não encontrado", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == cancelarButton)
		{
			try 
			{
				umCliente = clienteService.recuperaUmCliente(umCliente.getNumero());

				nomeTextField.setText(umCliente.getNome());
				
				
				
				cancelado();
			} 
			catch (ClienteNaoEncontradoException e1) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Cliente não encontrado", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == buscarButton)
		{	
			
		}
	}
	
	private boolean validaCliente()
	{
		boolean deuErro = false;
		if (nomeTextField.getText().trim().length() == 0)
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
		nomeTextField.setEnabled(true);
	
		
		nomeTextField.setText("");
		buttonGroup.clearSelection();
		

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo()
	{
		nomeTextField.setEnabled(false);
	

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void editavel()
	{
		nomeTextField.setEnabled(true);
	

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
	}

	public void removido()
	{
		nomeTextField.setEnabled(false);
		

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado()
	{
		nomeTextField.setEnabled(false);
		

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
