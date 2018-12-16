package servico;

import java.util.List;

import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;

//@Service
//public class ClienteService
public interface ClienteService
{	
	
	long inclui(Cliente umCliente); 
	
	void altera(Cliente umCliente) throws ClienteNaoEncontradoException;
	
	void exclui(long numero) throws ClienteNaoEncontradoException;
	
	Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException;
	
	List<Cliente> recuperaClientes();
	
	long recuperaQtdPeloNome(String nome); 
	
	List<Cliente> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina); 
	
	/*
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Transactional
	public long inclui(Cliente umCliente) 
	{	return clienteDAO.inclui(umCliente);
	}

	@Transactional
	public void altera(Cliente umCliente)
		throws ClienteNaoEncontradoException
	{	try
		{	clienteDAO.altera(umCliente);
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
			clienteDAO.exclui(umCliente.getNumero());
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
	{	return clienteDAO.recuperaClientes();
	}

	public long recuperaQtdPeloNome(String nome) 
	{	
		return clienteDAO.recuperaQtdPeloNome(nome + "%");
	}
	
	public List<Cliente> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina) 
	{	
		List<Cliente> clientes = clienteDAO.recuperaPeloNome(nome + "%", deslocamento, linhasPorPagina);

		return clientes;
	}*/
}