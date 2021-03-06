package aspecto;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectoParaLogDeErros 
{
	private static Logger logger = null;
	
//	@Pointcut("execution(* visao..*.*(..)) || execution(visao..*.new(..))")
	@Pointcut("call(* servico..*.*(..))")
	public void efetuaLogDeErro() {
		
	}
		

	@Around("efetuaLogDeErro()")
	public Object efetuaLog(ProceedingJoinPoint joinPoint) throws Throwable 
	{	
		
		try
		{	return joinPoint.proceed();
		}
		catch(Throwable throwable)
		{	
			String metodo = joinPoint.getSignature().getName();
	    	    	
	    	String mensagem = (throwable.getMessage() != null ? throwable.getMessage() : "");
			
	    	Throwable t = throwable.getCause();
			
	    	while ( t != null)
			{	
				mensagem = mensagem + (t.getMessage() != null ? " <==> " + t.getMessage() : ""); 
				t = t.getCause();
			}

			// As 4 linhas de c�digo abaixo geram o stack trace como um String
			StringWriter sw = new StringWriter();
	    	PrintWriter pw = new PrintWriter(sw);
	    	throwable.printStackTrace(pw); 	
	    	String stackTrace = sw.toString();

	    	logger = Logger.getLogger(this.getClass().getName());
	    	
			logger.error("   Classe do erro: " + throwable.getClass().getName() + "\n" +
					     "   Metodo: " + metodo + "\n" + 
					     "   Mensagem: " + mensagem + "\n" + 
					     "   Stack Trace: " + stackTrace);
	    	
	    	throw throwable;
		}
	}
}