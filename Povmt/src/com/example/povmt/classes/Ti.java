package com.example.povmt.classes;

public class Ti {
	private String atividade;
	private int hora;
	private int minuto;

	public Ti(String atividade, int hora, int minuto) {
		super();
		this.atividade = atividade;
		this.hora = hora;
		this.minuto = minuto;
	}

	public String getAtividade() {
		return atividade;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setAtividade(String novaAtividade) {
		atividade = novaAtividade;
	}

	public void setHora(int novaHora) {
		hora = novaHora;
	}

	public void setMinuto(int novoMinuto) {
		minuto = novoMinuto;
	}


}
