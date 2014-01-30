package br.com.setsoft;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.testng.annotations.Test;

import br.com.setsoft.utilidade.NumericoUtil;

public class TesteNumericoUtil {
	
	//dividirComArredondamentoParaCima
	@Test
	public void testDividirComArredondamentoParaCimaDeveriaRetornarDois() {
		
		BigDecimal dividendo = new BigDecimal("2.000000000000002");		
		BigDecimal divisor = new BigDecimal("2.0000000");
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(new Integer(2), quociente);
	}
	
	@Test
	public void testDividirComArredondamentoParaCimaInteger() {
		
		Integer dividendo = 3;
		Integer divisor = 2;		
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(new Integer(2), quociente);
	}
	
	@Test
	public void testDividirComArredondamentoParaCimaBigDecimal() {
		
		BigDecimal dividendo = new BigDecimal(3);
		BigDecimal divisor = new BigDecimal(2);		
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(new Integer(2), quociente);
	}
	
	@Test
	public void testDividirComArredondamentoParaCimaIntegerEBigDecimal() {
		
		Integer dividendo = 3;
		BigDecimal divisor = new BigDecimal(2);		
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(new Integer(2), quociente);
	}
	
	@Test
	public void testDividirComArredondamentoParaCimaDeveriaRetornarOMaximoInteiroQuandoQuocienteForIgualAOMaximoInteiro() {
		
		BigDecimal dividendo = new BigDecimal(Integer.MAX_VALUE).multiply(new BigDecimal(2));
		Integer divisor = 2;		
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(new BigDecimal(Integer.MAX_VALUE), new BigDecimal(quociente));
	}
	
	@Test
	public void testDividirComArredondamentoParaCimaDeveriaRetornarNullQuandoQuocienteMaiorQueOMaximoInteiro() {
		
		BigDecimal dividendo = new BigDecimal(Integer.MAX_VALUE).add(new BigDecimal("0.00000000000001"));
		Integer divisor = 1;		
		Integer quociente = NumericoUtil.dividirComArredondamentoParaCima(dividendo, divisor);
		
		Assert.assertEquals(null, quociente);
	}
	
	//dividir
	@Test
	public void testDividirDeveriaRetornarUmEMeio() {
		
		Integer dividendo = 3;
		Integer divisor = 2;		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertTrue(quociente.compareTo(new BigDecimal("1.50000000000000")) == 0);
	}
	
	@Test
	public void testDividirDeveriaRetornarOMaximoFloat() {
		
		Float dividendo = Float.MAX_VALUE;
		Integer divisor = 1;		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertEquals(dividendo.floatValue(), quociente.floatValue());
	}
	
	@Test
	public void testDividirDeveriaRetornarNullQuandoODividendoForIgualANull() {
		
		BigDecimal dividendo = null;
		BigDecimal divisor = new BigDecimal("2.00");		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertEquals(null, quociente);
	}
	
	@Test
	public void testDividirDeveriaRetornarNullQuandoODivisorForIgualANull() {
		
		BigDecimal dividendo = new BigDecimal("0.00000000000001");
		BigDecimal divisor = null;		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertEquals(null, quociente);
	}
	
	@Test
	public void testDividirDeveriaRetornarODividendoQuandoODivisorForIgualAUm() {
		
		BigDecimal dividendo = new BigDecimal("0.000000000000001");
		BigDecimal divisor = new BigDecimal("1.00");		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertEquals(dividendo, quociente);
	}
	
	@Test
	public void testDividirDeveriaRetornarNullQuandoODivisorForIgualAZERO() {
		
		BigDecimal dividendo = new BigDecimal("0.00000000000001");
		BigDecimal divisor = new BigDecimal("0.00");		
		BigDecimal quociente = NumericoUtil.dividir(dividendo, divisor);
		
		Assert.assertEquals(null, quociente);
	}
	
}