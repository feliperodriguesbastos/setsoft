package br.com.setsoft.conversor;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.setsoft.interfaces.IEntidadeBase;

@FacesConverter(value = "conversorEntidadeGenerico")
public class ConversorEntidadeGenerico implements Converter{
	
	@Override 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
        if (value != null) {  
            return this.getAttributesFrom(component).get(value);  
        }
        
        return null;  
    } 
	  
	@Override    
	@SuppressWarnings("rawtypes")
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
	  
        if (value != null && !"".equals(value)) { 
  
            IEntidadeBase entity = (IEntidadeBase) value;  
  
            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);  
  
            Object codigo = entity.getPK();
            
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return (String) value;  
    } 
	  
    @SuppressWarnings("rawtypes")
	protected void addAttribute(UIComponent component, IEntidadeBase o) {
    	
        String key = o.getPK().toString(); // codigo da entidade como chave neste caso  
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {
    	
        return component.getAttributes();  
    }

}
