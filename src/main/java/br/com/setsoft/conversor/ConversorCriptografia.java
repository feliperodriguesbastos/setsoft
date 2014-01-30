package br.com.setsoft.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.setsoft.utilidade.EncriptaSenha;

@FacesConverter(value = "conversorCriptografia")
public class ConversorCriptografia implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		return EncriptaSenha.encriptaSHA256(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		return value.toString();
	}
}
