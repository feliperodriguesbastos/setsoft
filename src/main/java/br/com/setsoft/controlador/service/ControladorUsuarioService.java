package br.com.setsoft.controlador.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.setsoft.crud.CrudGenerico;
import br.com.setsoft.modelo.Role;
import br.com.setsoft.modelo.Usuario;
import br.com.setsoft.utilidade.Fabrica;

public class ControladorUsuarioService extends CrudGenerico<Usuario, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Usuario> getClassePersistente() {
		return Usuario.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return Fabrica.getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> buscarTodosOsPerfis(){
		
		return getEntityManager().createQuery("SELECT A FROM Role A").getResultList(); 
	}

}
