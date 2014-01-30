package br.com.setsoft.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que representa operações de persistência.
 * 
 * @since Outubro 02, 2011
 * @author Joel Marques
 * @see http://community.jboss.org/wiki/GenericDataAccessObjects
 */
public interface ICrudGenerico<T, PK extends Serializable> {	

	
	public T buscarPorChave(PK primaryKey);

	public List<T> buscarPorFiltro(T filtro);

	public List<T> buscarTodos();
	
	public void adicionar(T objeto) throws Exception;
	
	public void alterar(T objeto) throws Exception ;
	
	public void excluir(T objeto) throws Exception;	
}