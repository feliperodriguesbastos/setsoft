package br.com.setsoft.utilidade;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public abstract class Fabrica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static ELContext getElContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}
	
	//entity Manager
	public static EntityManager getEntityManager() {		
		return (EntityManager) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(getElContext(), null, "entityManager");
	}
}