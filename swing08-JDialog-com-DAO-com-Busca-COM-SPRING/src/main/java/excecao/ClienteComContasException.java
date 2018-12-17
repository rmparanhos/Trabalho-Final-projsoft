package excecao;

import anotacao.ConstraintViolada;
import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
@ConstraintViolada(nome="CONTA_CLIENTE_FK")
public class ClienteComContasException extends RuntimeException
{
	private final static long serialVersionUID = 1;

	public ClienteComContasException()
	{	super();
	}

	public ClienteComContasException(String msg)
	{	super(msg);
	}
}
