package servico.impl;

import java.util.List;

import anotacao.RollbackFor;
import dao.ContaDAO;
import dao.controle.FabricaDeDAOs;
import excecao.ContaNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Conta;
import servico.ContaService;

public class ContaServiceImpl implements ContaService {

	private static ContaDAO contaDAO = FabricaDeDAOs.getDAO(ContaDAO.class);

	public long inclui(Conta umaConta) 
	{	
		System.out.println("\nDentro de ContaServiceImpl. Vai chamar o m�todo inclui() de ContaDAOImpl.");
		
		long numero = contaDAO.inclui(umaConta);
		
		System.out.println("\nDentro de ContaServiceImpl. Chamou o m�todo inclui() de ContaDAOImpl.");
		
		return numero;
	}

	@RollbackFor(nomes={ContaNaoEncontradaException.class})
	public void altera(Conta umaConta)
		throws ContaNaoEncontradaException
	{	
		try
		{	
			System.out.println("\nVai chamar o m�todo altera() de ContaDAOImpl.");

			contaDAO.altera(umaConta);
			
			System.out.println("\nChamou o m�todo altera() de ContaDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			throw new ContaNaoEncontradaException("Conta n�o encontrado");
		}
	}
	
	public void exclui(long id) 
		throws ContaNaoEncontradaException
	{	
		try
		{	
			System.out.println("Vai chamar o m�todo exclui() de ContaDAOImpl.");

			contaDAO.exclui(id);

			System.out.println("Chamou o m�todo exclui() de ContaDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
		    throw new ContaNaoEncontradaException("Conta n�o encontrado");
		}
	}

	public Conta recuperaUmaConta(long id) 
		throws ContaNaoEncontradaException
	{	
		try
		{	
			System.out.println("Vai chamar o m�todo recuperaUmaConta() de ContaDAOImpl.");

			Conta conta = contaDAO.recuperaUmaConta(id);
			
			System.out.println("Chamou o m�todo recuperaUmaConta() de ContaDAOImpl.");
			
			return conta;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ContaNaoEncontradaException("Conta n�o encontrado");
		}
	}

	public List<Conta> recuperaContas() 
	{	
		System.out.println("Vai chamar o m�todo recuperaContas() de ContaDAOImpl.");

		List<Conta> contas = contaDAO.recuperaContas();
		
		System.out.println("Chamou o m�todo recuperaContas() de ContaDAOImpl.");

		return contas;
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
