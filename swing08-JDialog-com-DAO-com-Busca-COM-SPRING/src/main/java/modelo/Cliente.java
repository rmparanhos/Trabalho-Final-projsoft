package modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries(
		{	
			@NamedQuery
			(	name = "Cliente.recuperaUmCliente",
				query = "select c from Cliente c order by c.nome asc"
			)
		})


@Entity
@Table(name="CLIENTE2")

public class Cliente 
{
	private Long numero;
	private String nome;
	private String dataNasc;
	
	public Cliente(String nome, String dataNasc) 
	{
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
		
	}

	public Cliente() 
	{	
	}

	public String toString()
	{	return "Numero = " + numero + " nome = " + nome + " data nascimento = " + dataNasc;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getNumero() 
	{	return numero;
	}

	@SuppressWarnings("unused")
	private void setNumero(Long numero) 
	{	this.numero = numero;
	}
	
	public String getNome() 
	{	return nome;
	}
	
	public void setNome(String nome) 
	{	this.nome = nome;
	}
	
	public String getDataNasc() 
	{	return dataNasc;
	}
	
	public void setDataNasc(String dataNasc) 
	{	this.dataNasc = dataNasc;
	}
}
	
	
