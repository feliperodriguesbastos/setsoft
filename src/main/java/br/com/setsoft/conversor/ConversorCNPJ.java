package br.com.setsoft.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.setsoft.utilidade.StringUtil;

@FacesConverter(value = "conversorCNPJ")
public class ConversorCNPJ implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
//		if (StringUtil.isPreenchida(value) && value.length() == 18) {
//			
//    		return value.replace(".", "").replace("-", "").replace("/", "");
//    	}
//    	
//    	return null;
		
		return value.replace(".", "").replace("-", "").replace("/", "");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if (StringUtil.isPreenchida((String)value) && ((String)value).length() == 14) {
			
			StringBuffer cnpj = new StringBuffer((String)value);
			cnpj.insert(12, "-");
			cnpj.insert(8, "/");
			cnpj.insert(5, ".");
			cnpj.insert(2, ".");
            
            return cnpj.toString();
        } 
    	
    	return null;
	}
}