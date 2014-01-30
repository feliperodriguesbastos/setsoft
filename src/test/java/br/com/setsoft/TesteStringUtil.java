package br.com.setsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

import br.com.setsoft.utilidade.StringUtil;

public class TesteStringUtil {
	
	@Test
	public void testIsPreenchidaDeveriaRetornarVerdadeiroQuandoConterPeloMenosUmCaracterDiferenteDeVazio() {
		
		String string = "  A  ";
		
		Assert.assertTrue(StringUtil.isPreenchida(string));
	}
	
	@Test
	public void testIsPreenchidaDeveriaRetornarFalsoQuandoTodosCaracteresForemVazios() {
		
		String string = "     ";
		
		Assert.assertFalse(StringUtil.isPreenchida(string));
	}
	
	@Test
	public void testIsPreenchidaDeveriaRetornarFalsoQuandoAstringForNula() {
		
		String string = null;
		
		Assert.assertFalse(StringUtil.isPreenchida(string));
	}
	
	@Test
	public void testConverterListaStringParaString() {
		
		String stringQueDeveriaRetornar = "joelmarques2008@hotmail.com; kerolenbrito2010@hotmail.com";
		
		List<String> listaString = Arrays.asList(stringQueDeveriaRetornar.split("; "));
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(listaString, "; ");
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringSemSeparadorQuandoSeparadorForNulo() {
		
		String stringQueDeveriaRetornar = "AEIOU";
		
		List<String> listaString = Arrays.asList("A", "E", "I", "O", "U");
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(listaString, null);		
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringSemCaracteresVazios() {
		
		String stringQueDeveriaRetornar = "A;E;I;O;U";
		
		List<String> listaString = Arrays.asList(" A", "E ", "  I     ", "O", "U");
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(listaString, ";");		
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringVaziaQuandoListaForVazia() {
		
		String stringQueDeveriaRetornar = "";
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(new ArrayList<String>(), ";");
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringVaziaQuandoListaForNula() {
		
		String stringQueDeveriaRetornar = "";
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(null, ";");
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringVaziaQuandoListaStringConterSomenteCaracteresVazios() {
		
		String stringQueDeveriaRetornar = "";
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(Arrays.asList("", " ", "  ", "   "), ";");
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterListaStringParaStringDeveriaRetornarUmaStringVaziaQuandoListaStringConterSomenteNulos() {
		
		String stringQueDeveriaRetornar = "";
		
		List<String> listaString = Arrays.asList(null, null, null);
		
		String stringQueRetornou = StringUtil.converterListaStringParaString(listaString, ";");
		
		Assert.assertTrue(stringQueDeveriaRetornar.equals(stringQueRetornou));		
	}
	
	@Test
	public void testConverterStringParaListaString() {
		
		String string = "A;E;I;O;U";
		
		List<String> listaQueDeveriaRetornar = Arrays.asList(string.split(";"));
		
		List<String> listaQueRetornou = StringUtil.converterStringParaListaString(string, ";");
		
		Assert.assertTrue(listaQueDeveriaRetornar.containsAll(listaQueRetornou));		
	}
	
	@Test
	public void testConverterStringParaListaStringDeveriaRetornarUmaListaVaziaQuandoAstringForVazia() {
		
		String string = "     ";
		
		List<String> listaQueRetornou = StringUtil.converterStringParaListaString(string, ";");
		
		Assert.assertTrue(listaQueRetornou.isEmpty());		
	}
	
	@Test
	public void testConverterStringParaListaStringDeveriaRetornarUmaListaVaziaQuandoAstringForNula() {
		
		String string = null;
		
		List<String> listaQueRetornou = StringUtil.converterStringParaListaString(string, ";");
		
		Assert.assertTrue(listaQueRetornou.isEmpty());		
	}
	
	@Test
	public void testConverterStringParaListaStringDeveriaRetornarUmaListaDaPropriaStringQuandoSeparadorForNulo() {
		
		String string = "A;E;I;O;U";
		
		List<String> listaQueDeveriaRetornar = Arrays.asList(string);
		
		List<String> listaQueRetornou = StringUtil.converterStringParaListaString(string, null);
		
		Assert.assertTrue(listaQueDeveriaRetornar.containsAll(listaQueRetornou));		
	}
	
	@Test
	public void testConverterStringParaListaStringDeveriaRetornarUmaListaDaPropriaStringQuandoSeparadorNaoEstaContidoNaString() {
		
		String string = "A;E;I;O;U";
		
		List<String> listaQueDeveriaRetornar = Arrays.asList(string);
		
		List<String> listaQueRetornou = StringUtil.converterStringParaListaString(string, "-");
		
		Assert.assertTrue(listaQueDeveriaRetornar.containsAll(listaQueRetornou));		
	}

}
