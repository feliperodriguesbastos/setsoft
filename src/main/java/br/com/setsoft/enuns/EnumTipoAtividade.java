package br.com.setsoft.enuns;

public enum EnumTipoAtividade {
	
	BUG("Bug"),
	MELHORIA("Melhoria"),
	MUDANCA("Mudança");
	
	private String tipo;
	
	private EnumTipoAtividade(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		
		return tipo;
	}

}
