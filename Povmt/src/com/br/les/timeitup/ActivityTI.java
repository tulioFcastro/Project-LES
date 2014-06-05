package com.br.les.timeitup;

public class ActivityTI {

	private String name;
	private int time;
	private int priority;

	public ActivityTI(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(int time) {
		this.time += time;
	}

	/*
	 * Retorna a prioridade
	 */
	public int getPriority() {
		return priority;
	}

	/*
	 * Muda o valor da prioridade
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
