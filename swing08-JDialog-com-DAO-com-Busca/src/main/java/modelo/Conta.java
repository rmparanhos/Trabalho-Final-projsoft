package modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTA")

public class Conta 
{
	private Long id;
	private String numero;
	private String dataAbr;
	private Cliente cliente;
	
	
	public Conta(String numero, String dataAbr, Cliente cliente) 
	{
		super();
		this.numero = numero;
		this.dataAbr = dataAbr;
		this.cliente =cliente;
		
	}

	public Conta() 
	{	
	}

	public String toString()
	{	return "id = " + id + "Numero = " + numero + " data abertura = " + dataAbr + " cliente = " + cliente.getNumero();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() 
	{	return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) 
	{	this.id = id;
	}
	
	public String getNumero() 
	{	return numero;
	}
	
	public void setNumero(String numero) 
	{	this.numero = numero;
	}
	
	public String getDataAbr() 
	{	return dataAbr;
	}
	
	public void setDataAbr(String dataAbr) 
	{	this.dataAbr = dataAbr;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENTE")
	public Cliente getCliente() 
	{	return cliente;
	}
	
	public void setClient(Cliente cliente) 
	{	this.cliente = cliente;
	}
}
	
	
