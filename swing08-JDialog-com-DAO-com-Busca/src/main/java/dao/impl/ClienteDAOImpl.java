package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.ClienteDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;
import servico.controle.JPAUtil;

//@Repository
public class ClienteDAOImpl implements ClienteDAO
{	
	public long inclui(Cliente umCliente) 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			em.persist(umCliente);
			
			return umCliente.getNumero();
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void altera(Cliente umCliente) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Cliente cliente = em.find(Cliente.class, umCliente.getNumero(), LockModeType.PESSIMISTIC_WRITE);
			
			if(cliente == null)
			{	throw new ObjetoNaoEncontradoException();
			}
		
			em.merge(umCliente);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void exclui(long numero) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();
		
			Cliente cliente = em.find(Cliente.class, numero, LockModeType.PESSIMISTIC_WRITE);
			
			if(cliente == null)
			{	throw new ObjetoNaoEncontradoException();
			}
	
			em.remove(cliente);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Cliente recuperaUmCliente(long numero) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Cliente umCliente = (Cliente)em.find(Cliente.class, new Long(numero));
			
			if (umCliente == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umCliente;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Cliente recuperaUmClienteComLock(long numero) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Cliente umCliente = em.find(Cliente.class, numero, LockModeType.PESSIMISTIC_WRITE);

			if (umCliente == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umCliente;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaClientes()
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			List<Cliente> clientes = em
				.createQuery("select c from Cliente c " +
						     "order by c.id asc")
				.getResultList();

			return clientes;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
	
	public long recuperaQtdPeloNome(String nome) 
	{	
		EntityManager em = JPAUtil.getEntityManager();
		long qtd = (Long) em.createQuery("select count(c) from Cliente c where c.nome like :nome")
						    .setParameter("nome", nome.toUpperCase())
							.getSingleResult();
		return qtd;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaPeloNome(String nome, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		EntityManager em = JPAUtil.getEntityManager();
		List<Cliente> clientes = em
			.createQuery("select c from Cliente c "
					   + "where c.nome like :nome order by c.nome asc")
			.setParameter("nome", nome.toUpperCase())
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return clientes;
	}
	
	/*@PersistenceContext
	private EntityManager em;

    public long inclui(Cliente umCliente) 
	{	
    	em.persist(umCliente);
		return umCliente.getNumero();
	}

	public void altera(Cliente umCliente) 
		throws ObjetoNaoEncontradoException 
	{	
		Cliente cliente = em.find(Cliente.class, umCliente.getNumero(), LockModeType.PESSIMISTIC_WRITE);
		
		if(cliente == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.merge(umCliente);
	}

    public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Cliente cliente = em.find(Cliente.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(cliente == null)
		{	throw new ObjetoNaoEncontradoException();
		}
		
		em.remove(cliente);
	}

    public Cliente recuperaUmCliente(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		Cliente umCliente = (Cliente)em
			.find(Cliente.class, new Long(numero));
			
		if (umCliente == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umCliente;
	}

	public Cliente recuperaUmClienteComLock(long numero) 
		throws ObjetoNaoEncontradoException 
	{	
		Cliente umCliente = (Cliente)em
			.find(Cliente.class, new Long(numero), LockModeType.PESSIMISTIC_WRITE);

		if (umCliente == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umCliente;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaClientes()
	{	
		List<Cliente> clientes = em
			.createQuery("select c from Cliente c " +
					     "order by c.id asc")
			.getResultList();

		return clientes;
	}

	public long recuperaQtdPeloNome(String nome) 
	{	
		long qtd = (Long) em.createQuery("select count(c) from Cliente c where c.nome like :nome")
						    .setParameter("nome", nome.toUpperCase())
							.getSingleResult();
		return qtd;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaPeloNome(String nome, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		List<Cliente> clientes = em
			.createQuery("select c from Cliente c "
					   + "where c.nome like :nome order by c.nome asc")
			.setParameter("nome", nome.toUpperCase())
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return clientes;
	}*/
}