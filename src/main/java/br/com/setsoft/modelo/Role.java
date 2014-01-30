package br.com.setsoft.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.setsoft.enuns.EnumPerfil;
import br.com.setsoft.interfaces.IEntidadeBase;

@Entity
@Table(name = "TA_ROLE")
public class Role implements IEntidadeBase<Integer>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_ROLE")	
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NOME_ROLE")
	private EnumPerfil nomeRole;

	public Role() {
		super();
	}

	public Role(Integer id, EnumPerfil nomeRole) {
		super();
		this.id = id;
		this.nomeRole = nomeRole;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumPerfil getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(EnumPerfil nomeRole) {
		this.nomeRole = nomeRole;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public Integer getPK() {
		return id;
	}

	@Override
	public boolean possuiChavePrimaria() {
		return this.id != null;
	}
}