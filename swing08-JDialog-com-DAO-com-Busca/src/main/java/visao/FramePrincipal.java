package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class FramePrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JMenuItem menuItemCliente;
	private JMenuItem menuItemSair;
	private JPanel panel;
	private JFrame frame;
	private JMenuItem mntmClientFf;
	private JMenuItem mntmConta;
	
	public FramePrincipal() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 382);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastro");
		menuBar.add(mnCadastrar);
	
		frame = this;
		
		menuItemCliente = new JMenuItem("Cliente");
		menuItemCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK)); // Diz a combinação necessaria para chamar os action listeners
		menuItemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogCliente dialogCliente = new DialogCliente(frame);
				dialogCliente.novo();
				dialogCliente.setVisible(true);
			}
		});
		mnCadastrar.add(menuItemCliente);
		
		menuItemSair = new JMenuItem("Sair");
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK)); // Diz a combinação necessaria para chamar os action listeners
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnCadastrar.add(menuItemSair);
		
		mntmClientFf = new JMenuItem("Client FF");
		mnCadastrar.add(mntmClientFf);
		mntmClientFf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogClienteFF dialogClienteFF = new DialogClienteFF(frame);
				dialogClienteFF.novo();
				dialogClienteFF.setVisible(true);
			}
		});
		
		mntmConta = new JMenuItem("Conta");
		mnCadastrar.add(mntmConta);
		mntmConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogConta dialogConta = new DialogConta(frame);
				dialogConta.novo();
				dialogConta.setVisible(true);
			}
		});
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 614, 325);
		getContentPane().add(panel);
	}
}
