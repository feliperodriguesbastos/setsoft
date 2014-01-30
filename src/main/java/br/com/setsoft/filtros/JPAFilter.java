package br.com.setsoft.filtros;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.setsoft.enuns.EnumPerfil;
import br.com.setsoft.enuns.EnumTipoPessoa;
import br.com.setsoft.modelo.Role;
import br.com.setsoft.modelo.Usuario;
import br.com.setsoft.utilidade.EncriptaSenha;


/**
 * Técnica: Open Session In View
 * 
 * Implementação da interface Filter do Servlet 3.0 para criação
 * da fábrica (factory) do gerenciador de entidades (entity manager),
 * para que uma mesma factory seja usada durante toda a execução da
 * aplicação, evitando assim, o LazyInitializationException do Hibernate
 * 
 * @autor: Joel Marques
 * @since Setembro 21, 2011
 * @see: http://blog.caelum.com.br/enfrentando-a-lazyinitializationexception-no-hibernate/
 * @see: http://community.jboss.org/wiki/OpenSessioninView
 */
//@WebFilter(servletNames = {"Faces Servlet"}) //foi mapeado no web.xml para permitir a ordem dos filtros. 
public class JPAFilter implements Filter {

	private EntityManagerFactory factory;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		EntityManager entityManager = this.factory.createEntityManager();
		request.setAttribute("entityManager", entityManager);
		entityManager.getTransaction().begin();		
		
		chain.doFilter(request, response);
		
		try {
			entityManager.getTransaction().commit();			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new ServletException(e);			
		}
		finally {
			entityManager.close();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.factory = Persistence.createEntityManagerFactory("unidadePersistence");
		
		if (administradorAindaNaoInserido()) {
			inserirDadosDoAdministrador();
		}
	}

	@Override
	public void destroy() {
		this.factory.close();
	}
	
	private boolean administradorAindaNaoInserido() {
		
		EntityManager entityManager = this.factory.createEntityManager();
		
		String JPQL = "SELECT X FROM Usuario X";
		
		return entityManager.createQuery(JPQL).getResultList().isEmpty();
	}
	
	private void inserirDadosDoAdministrador() {
		
		EntityManager entityManager = this.factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		inserirPerfisDeUsuario(entityManager);
		inserirUsuarioAdministrador(entityManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();		
	}
	
	private void inserirPerfisDeUsuario(EntityManager entityManager) {
		
		EnumPerfil roles[] = EnumPerfil.values();
		
		for (int i=0; i<roles.length; i++) {
			Role role = new Role(roles[i].getPosicao(), roles[i]);
			entityManager.persist(role);
		}
	}
	
	public void inserirUsuarioAdministrador(EntityManager entityManager){
		
		Usuario administrador = new Usuario();
		administrador.setTipoPessoa(EnumTipoPessoa.FISICA);
		administrador.setNome("Bit Marques");
		administrador.setEmail("bitmarques@gmail.com");
		administrador.setTelefone("9191876710");
		administrador.setEndereco("http://www.bitmarques.com");
		administrador.setDataCadastro(new Date());		
		administrador.setMatricula("bitmarques");
		administrador.setSenha(EncriptaSenha.encriptaSHA256(administrador.getMatricula()));
		
		Role role = new Role(EnumPerfil.ADMINISTRADOR.getPosicao(), EnumPerfil.ADMINISTRADOR);
		
		administrador.setRoles(Arrays.asList(role));
		administrador.setHabilitado(true);
		
		entityManager.persist(administrador);		
	}	
}