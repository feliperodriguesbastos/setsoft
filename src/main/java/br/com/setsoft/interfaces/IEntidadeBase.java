package br.com.setsoft.interfaces;

import java.io.Serializable;

public interface IEntidadeBase<PK extends Serializable> extends Serializable {
	
	public PK getPK();
	
	public boolean possuiChavePrimaria();
}
