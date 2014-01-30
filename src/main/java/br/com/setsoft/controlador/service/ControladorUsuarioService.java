package br.com.setsoft.controlador.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.setsoft.crud.CrudGenerico;
import br.com.setsoft.modelo.Role;
import br.com.setsoft.modelo.Usuario;
import br.com.setsoft.utilidade.Fabrica;

@ManagedBean(name = "controladorUsuarioService")
@SessionScoped
public class ControladorUsuarioService extends CrudGenerico<Usuario, Integer>{

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
