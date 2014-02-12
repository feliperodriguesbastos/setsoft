package br.com.setsoft.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.setsoft.interfaces.IEntidadeBase;
import br.com.setsoft.interfaces.arquivo.IArquivo;

@Entity
@Table(name = "TA_ARQUIVO")
@SequenceGenerator(name = "ArquivoSeq", sequenceName = "arquivo_seq")
public class Arquivo implements IEntidadeBase<Integer>, IArquivo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ARQUIVO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ArquivoSeq")
	private Integer id;
	
	@Lob
	@Column(name = "ARQUIVO", nullable = false)
	private byte[] arquivo;

	@Column(name = "TITULO_ARQUIVO", nullable = false, length = 300)
	private String tituloArquivo;
	
	public Arquivo() {
		super();
	}
	
	public Arquivo(byte[] arquivo, String tituloArquivo) {
		super();
		this.arquivo = arquivo;
		this.tituloArquivo = tituloArquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String getTituloArquivo() {
		return tituloArquivo;
	}

	public void setTituloArquivo(String tituloArquivo) {
		this.tituloArquivo = tituloArquivo;
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
		Arquivo other = (Arquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Integer getPK() {
		return this.id;
	}

	@Override
	public boolean possuiChavePrimaria() {
		return this.id != null;
	}

}