package br.com.setsoft.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//import javax.validation.constraints.Pattern;

@Entity
@Table(name = "TA_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "MATRICULA", length = 10, nullable = false, unique = true)
//	@Pattern(regexp = "[0-9]{5,10}",  message="Campo: Matrícula. Somente números são aceitos.")
	private String matricula;	

	@Column(name = "SENHA", length = 64, nullable = false)
	private String senha;
	
	@Column(name = "HABILITADO", nullable = false)
	private Boolean habilitado;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "AS_USUARIO_ROLE",
		joinColumns=@JoinColumn(name = "ID_PESSOA"),
		inverseJoinColumns=@JoinColumn(name = "ID_ROLE"))
	private List<Role> roles;

	public Usuario() {
		super();
	}
	
	public Usuario(String matricula) {
		super();
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Boolean getHabilitado() {		
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}