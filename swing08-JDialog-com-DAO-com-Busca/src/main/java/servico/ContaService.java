package servico;

import java.util.List;

import excecao.ContaNaoEncontradaException;
import modelo.Conta;

//@Service
//public class ContaService
public interface ContaService
{	
	
	long inclui(Conta umConta); 
	
	void altera(Conta umConta) throws ContaNaoEncontradaException;
	
	void exclui(long id) throws ContaNaoEncontradaException;
	
	Conta recuperaUmaConta(long id) throws ContaNaoEncontradaException;
	
	List<Conta> recuperaContas();
	
	long recuperaQtdPeloNumero(String numero); 
	
	List<Conta> recuperaPeloNumero(String numero, int deslocamento, int linhasPorPagina);
	/*
	@Autowired
	private ContaDAO contaDAO;
	
	@Transactional
	public long inclui(Conta umCliente) 
	{	return contaDAO.inclui(umCliente);
	}

	@Transactional
	public void altera(Conta umaConta)
		throws ContaNaoEncontradaException
	{	try
		{	contaDAO.altera(umaConta);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ContaNaoEncontradaException("Conta não encontrada");
		}
	}

	@Transactional
	public void exclui(Conta umaConta) 
		throws ContaNaoEncontradaException
	{	try
		{	
			contaDAO.exclui(umaConta.getId());
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ContaNaoEncontradaException("Conta não encontrada");
		}
	}

	public Conta recuperaUmaConta(long id) 
		throws ContaNaoEncontradaException
	{	try
		{	return contaDAO.recuperaUmaConta(id);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ContaNaoEncontradaException("Conta não encontrada");
		}
	}

	public List<Conta> recuperaContas()
	{	return contaDAO.recuperaContas();
	}

	public long recuperaQtdPeloNumero(String numero) 
	{	
		return contaDAO.recuperaQtdPeloNumero(numero + "%");
	}
	
	public List<Conta> recuperaPeloNumero(String numero, int deslocamento, int linhasPorPagina) 
	{	
		List<Conta> contas = contaDAO.recuperaPeloNumero(numero + "%", deslocamento, linhasPorPagina);

		return contas;
	}*/
}