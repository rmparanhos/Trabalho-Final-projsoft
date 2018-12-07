package dao;

import java.util.List;
import java.util.Set;

import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import modelo.Cliente;

import excecao.ObjetoNaoEncontradoException;

public interface ClienteDAO extends DaoGenerico<Cliente, Long>
{	
	
	/* ****** M�todos Gen�ricos ******* */

	@RecuperaObjeto
	Cliente recuperaUmCliente(long numero) 
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Cliente> recuperaListaDeClientes();
	
	@RecuperaUltimoOuPrimeiro
	Cliente recuperaPrimeiroCliente()
		throws ObjetoNaoEncontradoException;
	
	
	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
	
	/*
	public long inclui(Cliente umCliente); 

	public void altera(Cliente umCliente)
		throws ObjetoNaoEncontradoException; 
	
	public void exclui(long id) 
		throws ObjetoNaoEncontradoException; 
	
	public Cliente recuperaUmCliente(long numero) 
		throws ObjetoNaoEncontradoException; 

	public Cliente recuperaUmClienteComLock(long numero) 
			throws ObjetoNaoEncontradoException; 
	
	public List<Cliente> recuperaClientes();
	
	long recuperaQtdPeloNome(String nome);
	
	List<Cliente> recuperaPeloNome(String nome, 
         						   int deslocamento, 
            					   int linhasPorPagina);
            					   */
}