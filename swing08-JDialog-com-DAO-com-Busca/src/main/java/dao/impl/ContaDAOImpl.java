package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.ContaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Conta;
import servico.controle.JPAUtil;

//@Repository
public class ContaDAOImpl implements ContaDAO
{	
	public long inclui(Conta umaConta) 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			em.persist(umaConta);
			
			return umaConta.getId();
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void altera(Conta umaConta) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Conta conta = em.find(Conta.class, umaConta.getId(), LockModeType.PESSIMISTIC_WRITE);
			
			if(conta == null)
			{	throw new ObjetoNaoEncontradoException();
			}
		
			em.merge(umaConta);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();
		
			Conta conta = em.find(Conta.class, id, LockModeType.PESSIMISTIC_WRITE);
			
			if(conta == null)
			{	throw new ObjetoNaoEncontradoException();
			}
	
			em.remove(conta);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Conta recuperaUmaConta(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Conta umaConta = (Conta)em.find(Conta.class, new Long(id));
			
			if (umaConta == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umaConta;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Conta recuperaUmaContaComLock(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			Conta umaConta = em.find(Conta.class, id, LockModeType.PESSIMISTIC_WRITE);

			if (umaConta == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umaConta;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> recuperaContas()
	{	try
		{	
			EntityManager em = JPAUtil.getEntityManager();

			List<Conta> contas = em
				.createQuery("select c from Conta c " +
						     "order by c.id asc")
				.getResultList();

			return contas;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
	
	public long recuperaQtdPeloNumero(String numero) 
	{	
		EntityManager em = JPAUtil.getEntityManager();
		long qtd = (Long) em.createQuery("select count(c) from Conta c where c.numero like :numero")
						    .setParameter("numero", numero)
							.getSingleResult();
		return qtd;
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> recuperaPeloNumero(String numero, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		EntityManager em = JPAUtil.getEntityManager();
		List<Conta> contas = em
			.createQuery("select c from Conta c "
					   + "where c.numero like :numero order by c.numero asc")
			.setParameter("numero", numero)
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return contas;
	}
	/*
	@PersistenceContext
	private EntityManager em;

    public long inclui(Conta umaConta) 
	{	
    	em.persist(umaConta);
		return umaConta.getId();
	}

	public void altera(Conta umaConta) 
		throws ObjetoNaoEncontradoException 
	{	
		Conta conta = em.find(Conta.class, umaConta.getId(), LockModeType.PESSIMISTIC_WRITE);
		
		if(conta == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.merge(umaConta);
	}

    public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Conta conta = em.find(Conta.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(conta == null)
		{	throw new ObjetoNaoEncontradoException();
		}
		
		em.remove(conta);
	}

    public Conta recuperaUmaConta(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Conta umaConta = (Conta)em
			.find(Conta.class, new Long(id));
			
		if (umaConta == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umaConta;
	}

	public Conta recuperaUmaContaComLock(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Conta umaConta = (Conta)em
			.find(Conta.class, new Long(id), LockModeType.PESSIMISTIC_WRITE);

		if (umaConta == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umaConta;
	}

	@SuppressWarnings("unchecked")
	public List<Conta> recuperaContas()
	{	
		List<Conta> contas = em
			.createQuery("select c from Conta c " +
					     "order by c.id asc")
			.getResultList();

		return contas;
	}

	public long recuperaQtdPeloNumero(String numero) 
	{	
		long qtd = (Long) em.createQuery("select count(c) from Conta c where c.numero like :numero")
						    .setParameter("numero", numero)
							.getSingleResult();
		return qtd;
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> recuperaPeloNumero(String numero, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		List<Conta> contas = em
			.createQuery("select c from Conta c "
					   + "where c.numero like :numero order by c.numero asc")
			.setParameter("numero", numero)
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return contas;
	}
	*/
}