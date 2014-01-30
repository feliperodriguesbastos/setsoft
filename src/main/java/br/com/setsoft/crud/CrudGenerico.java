package br.com.setsoft.crud;


/**
 * Create - Adicionar nova entidade no banco de dados
 * Read(Retrieve) - Buscar entidades existentes no banco de dados
 * Update - Alterar entidades existentes no banco de dados
 * Delete - Excluir entidades existentes no banco de dados
 * 
 * @author Joel Marques
 */
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import br.com.setsoft.interfaces.ICrudGenerico;
import br.com.setsoft.interfaces.IEntidadeBase;

public abstract class CrudGenerico<T extends IEntidadeBase<PK>, PK extends Serializable> implements ICrudGenerico<T, PK> {

	/**
	 * Busca um registro na base de dados de acordo com a sua chave primária.
	 * 
	 * @param <primaryKey> a chave primária do objeto persistente.
	 * @return o objeto solicitado.
	 * @since Outubro 02, 2011
	 * @author Joel Marques
	 */	
	@Override
	public T buscarPorChave(PK primaryKey) {
		return getEntityManager().find(getClassePersistente(), primaryKey);
	}

	/**
	 * Busca registros na base de dados de acordo com o critério de busca.
	 * 
	 * @param <filtro> um critério de filtro.
	 * @return Uma lista de objetos do tipo especificado. 
	 * @since Outubro 02, 2011
	 * @author Joel Marques
	 */	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> buscarPorFiltro(T filtro) {		
		
		Session session = (Session) getEntityManager().getDelegate();
		Example example = criaExemplo(filtro);
		Criteria criteria = session.createCriteria(filtro.getClass()).add(example);
		
		return (List<T>) criteria.list();	
	}
	
	public Example criaExemplo(T filtro){
		
		Example example = Example.create(filtro);
		example.enableLike(MatchMode.ANYWHERE);
		example.excludeZeroes();
		example.ignoreCase();
		
		return example;
	}

	/**
	 * Busca todos os registros do tipo especificado.
	 * 
	 * @author Joel Marques
	 * @since Outubro 02, 2011
	 * @return Uma lista de objetos do tipo especificado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {		
		Query query = getEntityManager().createQuery("SELECT t FROM "+getClassePersistente().getSimpleName()+" t");
		List<T> lista = query.getResultList();		
		return lista;
	}
	
	/**
	 * Adiciona um registro na base de dados.
	 * 
	 * @param <objeto> especificado para persistência.
	 * @since Outubro 02, 2011
	 * @author Joel Marques
	 */
	@Override
	public void adicionar(T objeto) throws Exception {
		
		getEntityManager().persist(objeto);
		getEntityManager().flush();	
//		System.out.println("Adicionado com sucesso o objeto: "+objeto.getClass().getSimpleName());
	}
	
	/**
	 * Altera um registro na base de dados.
	 * 
	 * @param <objeto> especificado para persistência.
	 * @since Outubro 02, 2011
	 * @author Joel Marques
	 */
	@Override
	public void alterar(T objeto) throws Exception {
		
		getEntityManager().merge(objeto);
		getEntityManager().flush();
//		System.out.println("Alterado com sucesso o objeto: "+objeto.getClass().getSimpleName());
	}

	/**
	 * Exclui um registro na base de dados.
	 * 
	 * @param <objeto> especificado para persistência.
	 * @since Outubro 02, 2011
	 * @author Joel Marques
	 */
	@Override
	public void excluir(T objeto) throws Exception {
		
		getEntityManager().remove(getEntityManager().getReference(objeto.getClass(), objeto.getPK()));
		getEntityManager().flush();
//		System.out.println("Excluído com sucesso o objeto: "+objeto.getClass().getSimpleName());
	}
	
	protected abstract Class<T> getClassePersistente();

	protected abstract EntityManager getEntityManager();
}