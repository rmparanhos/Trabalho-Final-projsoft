package dao;

import java.util.List;

import modelo.Cliente;
import modelo.Conta;
import excecao.ObjetoNaoEncontradoException;

public interface ContaDAO
{	
	public long inclui(Conta umaConta); 

	public void altera(Conta umaConta)
		throws ObjetoNaoEncontradoException; 
	
	public void exclui(long id) 
		throws ObjetoNaoEncontradoException; 
	
	public Conta recuperaUmaConta(long id) 
		throws ObjetoNaoEncontradoException; 

	public Conta recuperaUmaContaComLock(long id) 
		throws ObjetoNaoEncontradoException; 
	
	public List<Conta> recuperaContas();
	
	long recuperaQtdPeloNumero(String numero);
	
	List<Conta> recuperaPeloNumero(String numero, 
         						   int deslocamento, 
            					   int linhasPorPagina);
}