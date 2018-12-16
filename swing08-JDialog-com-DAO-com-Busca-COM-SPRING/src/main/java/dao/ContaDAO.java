package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Conta;

public interface ContaDAO extends DaoGenerico<Conta, Long>
{	
	/* ****** M�todos Gen�ricos ******* */

	@RecuperaObjeto
	Conta recuperaUmaConta(long numero) 
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Conta> recuperaListaDeContas();
	
	@RecuperaUltimoOuPrimeiro
	Conta recuperaPrimeiraConta()
		throws ObjetoNaoEncontradoException;
	
	
	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
	
	long recuperaQtdPeloNumero(String numero);
	
	List<Conta> recuperaPeloNumero(String numero, 
			   int deslocamento, 
			   int linhasPorPagina);
	/*public long inclui(Conta umaConta); 

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
            					   */
}