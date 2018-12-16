package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;

public interface ClienteDAO extends DaoGenerico<Cliente, Long>
{	
	
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Cliente recuperaUmCliente(long numero) 
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Cliente> recuperaListaDeClientes();
	
	@RecuperaUltimoOuPrimeiro
	Cliente recuperaPrimeiroCliente()
		throws ObjetoNaoEncontradoException;
	
	
	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
	
	long recuperaQtdPeloNome(String nome);
	
	List<Cliente> recuperaPeloNome(String nome, 
			   int deslocamento, 
			   int linhasPorPagina);
	
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