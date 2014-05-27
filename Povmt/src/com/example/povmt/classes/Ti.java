package com.example.povmt.classes;

public class Ti {
	private String atividade;
	private int hora;
	private int minuto;
	private int segundos;

	public Ti(String atividade, int hora, int minuto, int segundos) {
		super();
		this.atividade = atividade;
		this.hora = hora;
		this.minuto = minuto;
		this.segundos = segundos;
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

	public int getSegundos() {
		return segundos;
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

	public void setSegundos(int novosSegundos) {
		segundos = novosSegundos;
	}

}
