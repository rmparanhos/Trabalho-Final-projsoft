package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.ClienteNaoEncontradoException;
import excecao.ContaNaoEncontradaException;
import modelo.Cliente;
import modelo.Conta;
import servico.ClienteService;
import servico.ContaService;

public class DialogConta extends JDialog implements ActionListener 
{
	private static final long serialVersionUID = 1L;

	private static ContaService contaService;
	private static ClienteService clienteService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	contaService = (ContaService)fabrica.getBean ("contaService");
    	clienteService = (ClienteService)fabrica.getBean("clienteService");
    }
	
	private JButton buscarClienteButton;
	private JButton cadastrarButton;
	private JButton buscarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton novoButton;
	private JButton editarButton;
	
	private JTextField numeroTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel numeroMensagem;
	private JLabel dataAbrMensagem;
	private JLabel clienteMensagem;

	private JPanel panel;
	
	private Conta umaConta;
	private Cliente umCliente;
	private JTextField dataAberturaTextField;
	private JTextField clienteTextField;
	private JButton cancelarButton;
	
	public void designaContaAFrame(Conta umaConta)
	{
		this.umaConta = umaConta;
		
		numeroTextField.setText(umaConta.getNumero());
		
		
		numeroMensagem.setText("");
		dataAbrMensagem.setText("");
		clienteMensagem.setText("");
	}
	
	public void designaClienteAFrame(Cliente umCliente)
	{
		this.umCliente = umCliente;
		
		numeroTextField.setText("");
		dataAberturaTextField.setText("");
		clienteTextField.setText(umCliente.getNome());
		
		this.novo();
		
	}
	public DialogConta(JFrame frame)
	{
		super(frame);
		
		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Contas");
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
		
		numeroMensagem = new JLabel("");
		numeroMensagem.setForeground(Color.RED);
		numeroMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		numeroMensagem.setBounds(138, 92, 241, 14);
		panel.add(numeroMensagem);
		
		
		buscarClienteButton = new JButton("Buscar Cliente");
		buscarClienteButton.setBounds(448, 257, 109, 23);
		panel.add(buscarClienteButton);
		buscarClienteButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(448, 85, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(448, 223, 96, 23);
		panel.add(buscarButton);
		buscarButton.addActionListener(this);
		
		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(448, 119, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(448, 153, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);
		
		novoButton = new JButton("Novo");
		novoButton.setBounds(448, 21, 96, 23);
		panel.add(novoButton);
		novoButton.addActionListener(this);
		
		editarButton = new JButton("Editar");
		editarButton.setBounds(448, 55, 96, 23);
		panel.add(editarButton);
		editarButton.addActionListener(this);
		
		
		JLabel clienteLabel = new JLabel("Cliente");
		clienteLabel.setBounds(89, 145, 39, 20);
		panel.add(clienteLabel);
		
		clienteTextField = new JTextField();
		clienteLabel.setLabelFor(clienteTextField);
		clienteTextField.setColumns(10);
		clienteTextField.setBackground(Color.WHITE);
		clienteTextField.setBounds(138, 144, 278, 20);
		panel.add(clienteTextField);
		
		dataAbrMensagem = new JLabel("");
		dataAbrMensagem.setForeground(Color.RED);
		dataAbrMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		dataAbrMensagem.setBounds(138, 125, 241, 14);
		panel.add(dataAbrMensagem);
		
		clienteMensagem = new JLabel("");
		clienteMensagem.setForeground(Color.RED);
		clienteMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		clienteMensagem.setBounds(138, 159, 241, 14);
		panel.add(clienteMensagem);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(448, 189, 96, 23);
		panel.add(cancelarButton);
		
		
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
			boolean deuErro = validaConta();
			
			if(!deuErro)
			{	
				umaConta = new Conta();
				umaConta.setNumero(numeroTextField.getText());
				umaConta.setDataAbr(dataAberturaTextField.getText());
				try
				{
				//umaConta.setCliente(clienteService.recuperaUmCliente(Long.parseLong(clienteTextField.getText())));
				System.out.println(umCliente);	
				umaConta.setCliente(umCliente);
				}
				//catch(ClienteNaoEncontradoException e1)
				catch(Exception e1)
				{
					novo();
					
					JOptionPane.showMessageDialog(this, "Cliente não encontrado", "", 
						JOptionPane.ERROR_MESSAGE);
				}

				contaService.inclui(umaConta);
				
				salvo();
				
				JOptionPane.showMessageDialog(this, "Conta cadastrada com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(obj == editarButton)
		{
			editavel();
		}
		else if(obj == alterarButton)
		{
			boolean deuErro = validaConta();
			
			if(!deuErro)
			{	
				umaConta.setNumero(numeroTextField.getText());
				umaConta.setDataAbr(dataAberturaTextField.getText());
				try
				{
				umaConta.setCliente(clienteService.recuperaUmCliente(Long.parseLong(clienteTextField.getText())));
				}
				catch(ClienteNaoEncontradoException e2)
				{
					novo();
					
					JOptionPane.showMessageDialog(this, "Cliente não encontrado", "", 
						JOptionPane.ERROR_MESSAGE);
				}
				try 
				{
					contaService.altera(umaConta);
					
					salvo();
					
					JOptionPane.showMessageDialog(this, "Conta atualizada com sucesso", "", 
							JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (ContaNaoEncontradaException e3) 
				{
					novo();
					
					JOptionPane.showMessageDialog(this, "Conta não encontrada", "", 
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(obj == removerButton)
		{
			try 
			{
				contaService.exclui(umaConta);

				removido();
				
				JOptionPane.showMessageDialog(this, "Conta removida com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (ContaNaoEncontradaException e4) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Conta não encontrado", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == cancelarButton)
		{
			try 
			{
				umaConta = contaService.recuperaUmaConta(umaConta.getId());

				numeroTextField.setText(umaConta.getNumero());
				
				
				
				cancelado();
			} 
			catch (ContaNaoEncontradaException e3) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Conta não encontrada", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == buscarButton)
		{	
			
		}
		else if(obj == buscarClienteButton)
		{	
			DialogTabelaClienteFF dialog = new DialogTabelaClienteFF(this);
			dialog.setVisible(true);
		}
	}
	
	private boolean validaConta()
	{
		boolean deuErro = false;
		if (numeroTextField.getText().trim().length() == 0)
		{	deuErro = true;
			numeroMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	numeroMensagem.setText("");
		}
		
		if (dataAberturaTextField.getText().trim().length() == 0)
		{	deuErro = true;
			dataAbrMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	dataAbrMensagem.setText("");
		}
		
		if (clienteTextField.getText().trim().length() == 0)
		{	deuErro = true;
			clienteMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	clienteMensagem.setText("");
		}
		
		
		
		return deuErro;
	}
	
	public void novo()
	{
		numeroTextField.setEnabled(true);
		dataAberturaTextField.setEnabled(true);
		clienteTextField.setEnabled(false);
		
		
		numeroTextField.setText("");
		dataAberturaTextField.setText("");
		
				

		buscarClienteButton.setEnabled(true);
		cadastrarButton.setEnabled(true);
		buscarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		editarButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo()
	{
		numeroTextField.setEnabled(false);
		dataAberturaTextField.setEnabled(false);
		clienteTextField.setEnabled(false);
		
		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
		buscarClienteButton.setEnabled(true);
	}

	public void editavel()
	{
		numeroTextField.setEnabled(true);
		dataAberturaTextField.setEnabled(true);
		clienteTextField.setEnabled(false);
		
		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
		buscarClienteButton.setEnabled(false);
	}

	public void removido()
	{
		numeroTextField.setEnabled(false);
		dataAberturaTextField.setEnabled(false);
		clienteTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
		buscarClienteButton.setEnabled(true);
	}

	public void cancelado()
	{
		numeroTextField.setEnabled(false);
		dataAberturaTextField.setEnabled(false);
		clienteTextField.setEnabled(false);
		
		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
		buscarClienteButton.setEnabled(true);
	}
}
