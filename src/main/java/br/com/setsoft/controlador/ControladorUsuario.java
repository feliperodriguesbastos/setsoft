package br.com.setsoft.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.DualListModel;

import br.com.setsoft.controlador.service.ControladorUsuarioService;
import br.com.setsoft.modelo.Role;
import br.com.setsoft.modelo.Usuario;

@ManagedBean(name = "controladorUsuario")
@ViewScoped
public class ControladorUsuario extends ControladorGenerico<Usuario, Integer> {
	
	private Usuario usuario;
	
	private List<Usuario> usuarios;
	
	private boolean exibirCampoSenha = false;
	
	private DualListModel<Role> perfilListModel;
	
	private List<Role> perfilList;
	
	@ManagedProperty(value = "#{controladorUsuarioService}")
	private ControladorUsuarioService controladorUsuarioService;
	
	@PostConstruct
	private void init() {
		usuario = new Usuario();
		usuarios = getDao().buscarTodos();
		perfilList = getDao().buscarTodosOsPerfis();
	}
	
	@Override
	public void prepararAdicionar(ActionEvent ae) {
		super.prepararAdicionar(ae);
		getObjeto().setDataCadastro(new Date());
		perfilListModel = new DualListModel<Role>(perfilList, new ArrayList<Role>());
		exibirCampoSenha = true;
	}
	
	@Override
	protected void executarPreOperacao() throws Exception {
		
		super.executarPreOperacao();
		
		if (isAdicionando() || isAlterando()){
			getObjeto().setRoles(perfilListModel.getTarget());
		}		
	}
	
	@Override
	protected void executarPosOperacao() throws Exception {
		super.executarPosOperacao();
		exibirCampoSenha = false;
		perfilListModel = null;
	}
	
	@Override
	public void cancelar(ActionEvent ae) {
		super.cancelar(ae);
		exibirCampoSenha = false;
		perfilListModel = null;
	}
	
	@SuppressWarnings("unchecked")
	private void montarPickList() {
		
		List<Role> perfilSource = (List<Role>)CollectionUtils.subtract(perfilList, getObjeto().getRoles());
				
		List<Role> perfilTarget = getObjeto().getRoles();
		
		perfilListModel = new DualListModel<Role>(perfilSource, perfilTarget);
	}
	
	public void exibirCampoSenha(ActionEvent ae) {
		exibirCampoSenha = !exibirCampoSenha;
	}
	
	@Override
	protected Usuario getObjeto() {
		return usuario;
	}

	@Override
	protected void setObjeto(Usuario objeto) {
		this.usuario = objeto;
	}

	@Override
	protected List<Usuario> getListaObjeto() {
		return usuarios;
	}
	
	@Override
	protected void setListaObjeto(List<Usuario> listaObjeto) {
		this.usuarios = listaObjeto;
	}

	@Override
	protected ControladorUsuarioService getDao() {
		return controladorUsuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		montarPickList();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ControladorUsuarioService getControladorUsuarioService() {
		return controladorUsuarioService;
	}

	public void setControladorUsuarioService(
			ControladorUsuarioService controladorUsuarioService) {
		this.controladorUsuarioService = controladorUsuarioService;
	}
	
	public boolean isExibirCampoSenha() {
		return exibirCampoSenha;
	}
	
	public DualListModel<Role> getPerfilListModel() {
		return perfilListModel;
	}
	
	public void setPerfilListModel(DualListModel<Role> perfilListModel) {
		this.perfilListModel = perfilListModel;
	}
	
}
