package br.com.setsoft.modelo;

import java.io.Serializable;


import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import br.com.setsoft.enuns.EnumTipoPessoa;
import br.com.setsoft.interfaces.IEntidadeBase;
import br.com.setsoft.utilidade.StringUtil;

@Entity
@Table(name = "TA_PESSOA")
@SequenceGenerator(name = "PessoaSeq", sequenceName = "pessoa_seq")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements IEntidadeBase<Integer>,Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PESSOA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PessoaSeq")
	private Integer id;

	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PESSOA", length = 8, nullable = true)
	private EnumTipoPessoa tipoPessoa;
	
	@Column(name = "IDENTIFICACAO_FISCAL", length = 14, nullable = true)
	private String identificacaoFiscal;
	
	@Column(name = "ENDERECO", length = 100, nullable = true)
	private String endereco;
	
	@Column(name = "NUMERO", nullable = true)
	private Integer numero;
	
	@Column(name = "EMAIL", length = 100, nullable = true)
	@Email(message = "Campo: E-mail. Não é um e-mail válido.")
	private String email;	
	
	@Column(name = "TELEFONE", length = 14, nullable = true)
	private String telefone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = true)
	private Date dataCadastro;	
	
	public Pessoa() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getIdentificacaoFiscal() {
		return identificacaoFiscal;
	}

	public void setIdentificacaoFiscal(String identificacaoFiscal) {
		this.identificacaoFiscal = identificacaoFiscal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}	

	@Override
	public Integer getPK() {
		return id;
	}

	@Override
	public boolean possuiChavePrimaria() {
		return this.id != null;
	}
	
	public boolean isPessoaFisica() {
		
		return EnumTipoPessoa.FISICA.equals(tipoPessoa);
	}
	
	public boolean isPessoaJuridica() {
		
		return EnumTipoPessoa.JURIDICA.equals(tipoPessoa);
	}
	
	public boolean isTipoPessoaPreenchido() {
		
		return this.tipoPessoa != null;
	}
	
	public EnumTipoPessoa[] getTiposDePessoa() {
		
		return EnumTipoPessoa.values();
	}
	
	public String numeroComoString() {
		
		return numero == null ? "" : numero.toString();
	}
	
	public String nomeMaisIdentificacaoFiscal() {
		
		return StringUtil.converterListaStringParaString(Arrays.asList(this.nome, this.identificacaoFiscal), " - ");
	}
	
	public boolean possuiEmail() {
		
		return StringUtil.isPreenchida(this.email);
	}
}