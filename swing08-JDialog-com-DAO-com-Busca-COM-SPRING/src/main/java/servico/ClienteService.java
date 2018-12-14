package servico;

import java.util.List;

import modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;

//@Service
public class ClienteService
{	
	private ClienteDAO clienteDAO = null;

	
	@Autowired
	public void setClienteDAO(ClienteDAO clienteDAO)
	{	
		this.clienteDAO = clienteDAO;
	}
	
	@Transactional
	public long inclui(Cliente umCliente) 
	{	return clienteDAO.inclui(umCliente).getNumero();
	}

	@Transactional
	public void altera(Cliente umCliente)
		throws ClienteNaoEncontradoException
	{	try
		{	clienteDAO.getPorId(umCliente.getNumero());
			clienteDAO.altera(umCliente);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	@Transactional
	public void exclui(Cliente umCliente) 
		throws ClienteNaoEncontradoException
	{	try
		{	
			System.out.println("oi");
			Cliente cliente = clienteDAO.recuperaUmCliente(umCliente.getNumero());
			System.out.println(cliente);
			clienteDAO.exclui(cliente);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	public Cliente recuperaUmCliente(long numero) 
		throws ClienteNaoEncontradoException
	{	try
		{	return clienteDAO.recuperaUmCliente(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	public List<Cliente> recuperaClientes()
	{	return clienteDAO.recuperaListaDeClientes();
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