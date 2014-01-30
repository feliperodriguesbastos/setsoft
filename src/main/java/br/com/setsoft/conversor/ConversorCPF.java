package br.com.setsoft.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.setsoft.utilidade.StringUtil;


@FacesConverter(value = "conversorCPF")
public class ConversorCPF implements Converter {

    @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	
//    	if (StringUtil.isPreenchida(value) && value.length() == 14) {
//    		return value.replace(".", "").replace("-", "");    		
//    	}
//    	
//    	return null;
    	
    	return value.replace(".", "").replace("-", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	
		if (StringUtil.isPreenchida((String)value) && ((String)value).length() == 11) {
		    		
			StringBuffer cpf = new StringBuffer((String)value);
			cpf.insert(9, "-");
			cpf.insert(6, ".");
			cpf.insert(3, ".");
            
            return cpf.toString();
        }
    	
    	return null;
    }
}