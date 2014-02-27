package br.com.setsoft.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.setsoft.controlador.service.ControladorUsuarioService;
import br.com.setsoft.modelo.Usuario;

@Named
@SessionScoped
public class ControladorLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ControladorUsuarioService controladorUsuarioService;
	
	private Usuario usuario;
	
	@PostConstruct
	private void init() {
		
		this.definirUsuarioLogado();		
	}
	
	private void definirUsuarioLogado() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if (context instanceof SecurityContext) {
			
			Authentication authentication = context.getAuthentication();
			
			if (authentication instanceof Authentication) {
				
				String login = ((User)authentication.getPrincipal()).getUsername();
				
				List<Usuario> usuarios = controladorUsuarioService.buscarPorFiltro(new Usuario(login));
				
				if (!usuarios.isEmpty()) {
					usuario = usuarios.get(0);
				}
				
//				List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
			}
		}
	}

	public void setControladorUsuarioService(ControladorUsuarioService controladorUsuarioService) {
		
		this.controladorUsuarioService = controladorUsuarioService;
	}

	public Usuario getUsuario() {
		
		return usuario;
	}
	
}