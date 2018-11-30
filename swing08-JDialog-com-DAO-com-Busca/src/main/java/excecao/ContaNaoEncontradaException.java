package excecao;


public class ContaNaoEncontradaException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public ContaNaoEncontradaException(String msg)
	{	super(msg);
	}

	public ContaNaoEncontradaException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	