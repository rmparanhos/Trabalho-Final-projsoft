package servico.impl;

import java.util.List;

import anotacao.RollbackFor;
import dao.ClienteDAO;
import dao.controle.FabricaDeDAOs;
import excecao.ClienteNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;
import servico.ClienteService;

public class ClienteServiceImpl implements ClienteService{

	private static ClienteDAO clienteDAO = FabricaDeDAOs.getDAO(ClienteDAO.class);

	public long inclui(Cliente umCliente) 
	{	
		System.out.println("\nDentro de ClienteServiceImpl. Vai chamar o m�todo inclui() de ClienteDAOImpl.");
		
		long numero = clienteDAO.inclui(umCliente);
		
		System.out.println("\nDentro de ClienteServiceImpl. Chamou o m�todo inclui() de ClienteDAOImpl.");
		
		return numero;
	}

	@RollbackFor(nomes={ClienteNaoEncontradoException.class})
	public void altera(Cliente umCliente)
		throws ClienteNaoEncontradoException
	{	
		try
		{	
			System.out.println("\nVai chamar o m�todo altera() de ClienteDAOImpl.");

			clienteDAO.altera(umCliente);
			
			System.out.println("\nChamou o m�todo altera() de ClienteDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			throw new ClienteNaoEncontradoException("Cliente n�o encontrado");
		}
	}
	
	public void exclui(long numero) 
		throws ClienteNaoEncontradoException
	{	
		try
		{	
			System.out.println("Vai chamar o m�todo exclui() de ClienteDAOImpl.");

			clienteDAO.exclui(numero);

			System.out.println("Chamou o m�todo exclui() de ClienteDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
		    throw new ClienteNaoEncontradoException("Cliente n�o encontrado");
		}
	}

	public Cliente recuperaUmCliente(long numero) 
		throws ClienteNaoEncontradoException
	{	
		try
		{	
			System.out.println("Vai chamar o m�todo recuperaUmCliente() de ClienteDAOImpl.");

			Cliente cliente = clienteDAO.recuperaUmCliente(numero);
			
			System.out.println("Chamou o m�todo recuperaUmCliente() de ClienteDAOImpl.");
			
			return cliente;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ClienteNaoEncontradoException("Cliente n�o encontrado");
		}
	}

	public List<Cliente> recuperaClientes() 
	{	
		System.out.println("Vai chamar o m�todo recuperaProdutos() de ProdutoDAOImpl.");

		List<Cliente> clientes = clienteDAO.recuperaClientes();
		
		System.out.println("Chamou o m�todo recuperaProdutos() de ProdutoDAOImpl.");

		return clientes;
	}
	
	public long recuperaQtdPeloNome(String nome) 
	{	
		return clienteDAO.recuperaQtdPeloNome(nome + "%");
	}
	
	public List<Cliente> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina) 
	{	
		List<Cliente> clientes = clienteDAO.recuperaPeloNome(nome + "%", deslocamento, linhasPorPagina);

		return clientes;
	}

}
