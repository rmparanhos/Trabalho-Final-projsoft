package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ContaDAO;
import excecao.ContaNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Conta;

//@Service
public class ContaService
{	
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
	}
}