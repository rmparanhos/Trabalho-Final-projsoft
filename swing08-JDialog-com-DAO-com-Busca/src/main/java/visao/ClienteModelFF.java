package visao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;
import servico.ClienteService;
import servico.controle.FabricaDeServico;

public class ClienteModelFF extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	public static final int COLUNA_NUMERO = 0;
	public static final int COLUNA_NOME = 1;
	public static final int COLUNA_DATA_NASC = 2;
	public static final int COLUNA_ACAO = 3;
	
    private final static int NUMERO_DE_LINHAS_POR_PAGINA = 6;
	
	private static ClienteService clienteService = FabricaDeServico.getServico(ClienteService.class);;

    private Map<Integer, Cliente> cache;
    private int rowIndexAnterior = 0;
    
    private Integer qtd;
    private String nomeCliente;
    
    public ClienteModelFF()
	{	
    	this.qtd = null;
		this.cache = new HashMap<Integer, Cliente>(NUMERO_DE_LINHAS_POR_PAGINA * 4 / 75 / 100 + 2);
	}

    public void setNomeCliente(String nomeCliente)
    {
    	this.nomeCliente = nomeCliente;
    }
    
	public String getColumnName(int c)
	{
		if(c == COLUNA_NUMERO) return "Número";
		if(c == COLUNA_NOME) return "Nome";
		if(c == COLUNA_DATA_NASC) return "Data Nascimento";
		if(c == COLUNA_ACAO) return "Ação";
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if(qtd == null)
			qtd = (int)clienteService.recuperaQtdPeloNome(nomeCliente);

		return qtd;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{   
		if (!cache.containsKey(rowIndex)) 
		{	
			if(cache.size() > (NUMERO_DE_LINHAS_POR_PAGINA * 3))
			{	
				cache.clear();
				
				if(rowIndex >= rowIndexAnterior) 
				{
					List<Cliente> resultados = clienteService.recuperaPeloNome(nomeCliente, rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);
				
					for (int j = 0; j < resultados.size(); j++) 
					{	Cliente cliente = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, cliente);
					}
				}
				else
				{
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0) inicio = 0;
				
					List<Cliente> resultados = clienteService.recuperaPeloNome(nomeCliente, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Cliente cliente = resultados.get(j);
						cache.put(inicio + j, cliente);
					}
				}
			}
			else
			{
				if(rowIndex >= rowIndexAnterior) 
				{
					List<Cliente> resultados = clienteService.recuperaPeloNome(nomeCliente, rowIndex, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Cliente cliente = resultados.get(j);
						cache.put(rowIndex + j, cliente);
					}
				}
				else
				{
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0) inicio = 0;

					List<Cliente> resultados = clienteService.recuperaPeloNome(nomeCliente, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Cliente cliente = resultados.get(j);
						cache.put(inicio + j, cliente);
					}
				}
			}
        }

		rowIndexAnterior = rowIndex;
        
        Cliente cliente = cache.get(rowIndex);

		if(columnIndex == COLUNA_NUMERO)
			return cliente.getNumero();
		else if (columnIndex == COLUNA_NOME)
			return cliente.getNome();
		else if (columnIndex == COLUNA_DATA_NASC)
			return cliente.getDataNasc();
		else
			return null;
	}
	
	// Para que os campos booleanos sejam renderizados como check box.
	// Neste caso, não há campo boleano.
	public Class<?> getColumnClass(int c)
	{
		Class<?> classe = null;
		if(c == COLUNA_NUMERO) classe = Integer.class;
		if(c == COLUNA_NOME) classe = String.class;
		if(c == COLUNA_DATA_NASC) classe = String.class;
		if(c == COLUNA_ACAO) classe = ButtonColumn.class;
		return classe;
	}
	
	// Para que as células referentes às colunas 1 em diante possam ser editadas
	public boolean isCellEditable(int r, int c)
	{
		return c != 0;
	}

	@Override
	public void setValueAt(Object obj, int r, int c) 
	{
		Cliente umCliente = cache.get(r);
		if(c == COLUNA_NOME) umCliente.setNome((String)obj);
		if(c == COLUNA_DATA_NASC) umCliente.setDataNasc((String)obj);
		try 
		{	clienteService.altera(umCliente);
		} 
		catch (ClienteNaoEncontradoException e) 
		{	e.printStackTrace();
		}
	}
}
