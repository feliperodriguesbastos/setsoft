package br.com.setsoft.modelo;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.setsoft.enuns.EnumTipoAtividade;
import br.com.setsoft.interfaces.IEntidadeBase;
import br.com.setsoft.interfaces.arquivo.IArquivo;
import br.com.setsoft.interfaces.arquivo.ISuporteArquivo;

@Entity
@Table(name = "TA_FEEDBACK")
@SequenceGenerator(name = "FeedbackSeq", sequenceName = "feedback_seq")
public class Feedback implements IEntidadeBase<Integer>, ISuporteArquivo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_FEEDBACK")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FeedbackSeq")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_FEEDBACK", nullable = false)
	private EnumTipoAtividade tipoFeedback;

	@Column(name = "DESCRICAO", length = 1000, nullable = false)
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	private Usuario colaborador;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;
	
	@OneToMany(cascade = {javax.persistence.CascadeType.PERSIST, 
			   javax.persistence.CascadeType.MERGE, 
			   javax.persistence.CascadeType.REMOVE},
			   fetch=FetchType.EAGER)
	@JoinTable(name = "AS_FEEDBACK_ARQUIVO",
			   joinColumns=@JoinColumn(name = "ID_FEEDBACK"),
			   inverseJoinColumns=@JoinColumn(name = "ID_ARQUIVO"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Arquivo> arquivos = new ArrayList<Arquivo>();
	
	public Feedback() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumTipoAtividade getTipoFeedback() {
		return tipoFeedback;
	}

	public void setTipoFeedback(EnumTipoAtividade tipoFeedback) {
		this.tipoFeedback = tipoFeedback;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getColaborador() {
		return colaborador;
	}

	public void setColaborador(Usuario colaborador) {
		this.colaborador = colaborador;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
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
		Feedback other = (Feedback) obj;
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
	
	public EnumTipoAtividade[] getTiposDeFeedBack() {
		
		return EnumTipoAtividade.values();
	}

	@Override
	public void adicionarArquivo(IArquivo arquivo) {
		
		if (arquivo != null) {
			this.arquivos.add((Arquivo)arquivo);
		}
	}

	@Override
	public void removerArquivo(IArquivo arquivo) {
		
		if (arquivo != null) {
			this.arquivos.remove(arquivo);
		}
	}

}