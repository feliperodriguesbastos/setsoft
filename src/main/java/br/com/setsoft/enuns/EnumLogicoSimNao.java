package br.com.setsoft.enuns;

public enum EnumLogicoSimNao {
	
	SIM("Sim", true),
	NAO("Não", false);
	
	private String label;
	private Boolean valor;

	private EnumLogicoSimNao(String label, Boolean valor) {
		this.label = label;
		this.valor = valor;
	}

	public String getLabel() {
		return label;
	}
	
	public Boolean getValor() {
		return valor;
	}

}