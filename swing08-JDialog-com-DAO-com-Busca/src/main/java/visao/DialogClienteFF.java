package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import modelo.Cliente;
import servico.ClienteService;

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
	
	private JLabel nomeMensagem;
	
	private JPanel panel;
	
	private Cliente umCliente;
	private JTextField dataNascTextField;
	private JLabel dataNascMensagem;
	
	public void designaClienteAFrame(Cliente umCliente)
	{
		this.umCliente = umCliente;
		
		nomeTextField.setText(umCliente.getNome());
		//dataNascTextField.setText(umCliente.getNasc());
		
		nomeMensagem.setText("");
		dataNascMensagem.setText("");
		
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
		
		JLabel cadastrarClientesLabel = new JLabel("Cadastro de Clientes");
		cadastrarClientesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarClientesLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarClientesLabel.setBounds(189, 21, 190, 26);
		panel.add(cadastrarClientesLabel);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(95, 78, 45, 14);
		panel.add(nomeLabel);
		
		nomeTextField = new JTextField();
		nomeLabel.setLabelFor(nomeTextField);
		nomeTextField.setBounds(138, 75, 278, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(50);
		nomeTextField.setBackground(SystemColor.window);
		
		JLabel dataNascLabel = new JLabel("Data Nascimento");
		dataNascLabel.setBounds(40, 113, 100, 20);
		panel.add(dataNascLabel);
		
		dataNascTextField = new JTextField();
		dataNascLabel.setLabelFor(dataNascTextField);
		dataNascTextField.setBounds(138, 113, 278, 20);
		panel.add(dataNascTextField);
		dataNascTextField.setColumns(10);
		dataNascTextField.setBackground(SystemColor.window);
		
		nomeMensagem = new JLabel("");
		nomeMensagem.setForeground(Color.RED);
		nomeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nomeMensagem.setBounds(138, 92, 241, 14);
		panel.add(nomeMensagem);
		
		
		novoButton = new JButton("Novo");
		novoButton.setBounds(451, 52, 96, 23);
		panel.add(novoButton);
		novoButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(451, 122, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);
		
		editarButton = new JButton("Editar");
		editarButton.setBounds(451, 88, 96, 23);
		panel.add(editarButton);
		editarButton.addActionListener(this);
		
		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(451, 156, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(451, 190, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(451, 224, 96, 23);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(this);

		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(451, 258, 96, 23);
		panel.add(buscarButton);
		
		dataNascMensagem = new JLabel("");
		dataNascMensagem.setForeground(Color.RED);
		dataNascMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		dataNascMensagem.setBounds(138, 131, 241, 14);
		panel.add(dataNascMensagem);
		
		
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
				umCliente.setDataNasc(dataNascTextField.getText().toUpperCase());

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
				umCliente.setDataNasc(dataNascTextField.getText());

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
		
		if (dataNascTextField.getText().trim().length() == 0)
		{	deuErro = true;
			dataNascMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	dataNascMensagem.setText("");
		
		}
		
		
		
		return deuErro;
	}
	
	public void novo()
	{
		nomeTextField.setEnabled(true);
		dataNascTextField.setEnabled(true);
				
		nomeTextField.setText("");
		dataNascTextField.setText("");
		
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
		dataNascTextField.setEnabled(false);

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
		dataNascTextField.setEnabled(true);

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
		dataNascTextField.setEnabled(false);

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
		dataNascTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
