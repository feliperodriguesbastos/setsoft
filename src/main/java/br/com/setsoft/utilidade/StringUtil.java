package br.com.setsoft.utilidade;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtil {
	
	public static boolean isNull(String string) {
		
		return string == null;
	}
	
	public static boolean isNotNull(String string) {
		
		return string != null;
	}
	
	public static boolean isPreenchida(String string) {
		
		return org.apache.commons.lang3.StringUtils.isNotBlank(string);
	}
	
	public static String converterListaStringParaString(List<String> lista, String separador) {
		
		StringBuilder builder = new StringBuilder();
		
		if (lista != null) {
			for (String string : lista) {
				
				if (isPreenchida(string)) {
					
					if (isNotNull(separador) && builder.length() > 0) {
						builder.append(separador);
					}
					
					builder.append(string.trim());				
				}
			}
		}
		
		return builder.toString();
		
//		return org.apache.commons.lang3.StringUtils.join(lista, separador);
	}
	
	public static List<String> converterStringParaListaString(String string, String separador) {
		
		if (isPreenchida(string)) {
			
			if (isNotNull(separador)) {
				return Arrays.asList(string.split(separador));
			}
			
			return Arrays.asList(string);
		}
		
		return Collections.emptyList();
	}
}