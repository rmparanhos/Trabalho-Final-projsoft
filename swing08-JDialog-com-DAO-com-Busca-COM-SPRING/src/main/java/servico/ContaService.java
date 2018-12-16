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
	private ContaDAO contaDAO = null;
	
	@Autowired
	public void setContaDAO(ContaDAO contaDAO)
	{	
		this.contaDAO = contaDAO;
	}
	
	@Transactional
	public long inclui(Conta umaConta) 
	{	
		//return contaDAO.inclui(umaConta);
		return contaDAO.inclui(umaConta).getId();
	}

	@Transactional
	public void altera(Conta umaConta)
		throws ContaNaoEncontradaException
	{	try
		{	
			contaDAO.getPorId(umaConta.getId());
			contaDAO.altera(umaConta);
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
			//contaDAO.exclui(umaConta.getId());
			Conta conta = contaDAO.recuperaUmaConta(umaConta.getId());
			contaDAO.exclui(conta);
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
	{	return contaDAO.recuperaListaDeContas();
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