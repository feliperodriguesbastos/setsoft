package br.com.setsoft.utilidade;

import java.math.BigDecimal;

public class NumericoUtil {
	
	public static Integer dividirComArredondamentoParaCima(Number dividendo, Number divisor) {
		
		BigDecimal quociente = dividir(dividendo, divisor);
		
		if (quociente == null || quociente.compareTo(new BigDecimal(Integer.MAX_VALUE)) == 1) {
			return null;
		}
		
		Integer resultado = quociente.setScale(0, BigDecimal.ROUND_UP).intValue();
		
		return resultado;
	}
	
	public static BigDecimal dividir(Number dividendo, Number divisor) {
		
		//se algum dos parâmetros for null, retorna null.
		if (dividendo == null || divisor == null) {
			return null;
		}
		
		BigDecimal divisorParam = new BigDecimal(divisor.toString());
		
		//se o divisor for igual a zero, retorna null.
		if (divisorParam.compareTo(new BigDecimal(0)) == 0) {
			return null;
		}
		
		BigDecimal dividendoParam = new BigDecimal(dividendo.toString());
		
		return dividendoParam.divide(divisorParam);
	}
	
	//TODO criar teste
	public static BigDecimal somar(Number... valores) {
		
		BigDecimal soma = BigDecimal.ZERO;
		
		for (Number numero : valores) {
			
			soma = soma.add(trocaNuloPorZero(numero));
		}
		
		return soma;
	}
	
	//TODO criar teste
	public static BigDecimal subtrair(Number minuendo, Number subtraendo) {
		
		return trocaNuloPorZero(minuendo).subtract(trocaNuloPorZero(subtraendo));
	}
	
	//TODO criar teste
	public static BigDecimal trocaNuloPorZero(Number numero){
		
		return numero == null ? new BigDecimal("0.00") : new BigDecimal(numero.toString()); 
	}
	
	public static String  valorComoString(BigDecimal valor) {
		
		return valor == null ? null : valor.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
	}
}