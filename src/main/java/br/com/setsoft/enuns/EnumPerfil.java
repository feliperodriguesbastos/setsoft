package br.com.setsoft.enuns;

public enum EnumPerfil {
	
	SYSTEM_USER(1,"Usuário Padrão"),
	ADMINISTRADOR(2,"Administrador"),
	SUPERVISOR(3, "Supervisor");
	
	private String tipo;
	
	private int posicao;
	
	
	private EnumPerfil(Integer i, String t) {
		posicao = i;
		tipo = t;
	}

	public String getTipo() {
		return tipo;
	}
	
	public int getPosicao(){
		return posicao;
	}
}