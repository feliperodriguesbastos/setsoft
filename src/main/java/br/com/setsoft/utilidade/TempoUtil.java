package br.com.setsoft.utilidade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TempoUtil {
	
	public static Date formatarDataddMMyyyy(Date data){
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataFormatada = null;
		try {
			String dataString = formataData.format(data);
			dataFormatada = formataData.parse(dataString);
		} catch (ParseException e) {
			dataFormatada = data;
			e.printStackTrace();
		}
		
		return dataFormatada;
	}
	
	public static String formatarDataddMMyyyyComoString(Date data) {
		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

		return formataData.format(data);
	}
}