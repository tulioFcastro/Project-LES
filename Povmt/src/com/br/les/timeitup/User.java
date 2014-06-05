package com.br.les.timeitup;

import java.util.Calendar;

public class User {
	private Week[] weeks;
	private int id;
	private String name;
	private String email;

	public User() {
		weeks = new Week[3];
		initializeWeeks();
	}

	public User(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		initializeWeeks();
	}

	/*
	 * Inicializando as semanas
	 */
	private void initializeWeeks() {
		this.weeks = new Week[3];
		for (int i = 0; i < this.weeks.length; i++) {
			this.weeks[i] = new Week();
		}
		this.weeks[0].decrementNum(2);
		this.weeks[1].decrementNum(1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Retorna o name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Muda o valor do name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Retorna o email
	 */
	public String getEmail() {
		return email;
	}

	/*
	 * Muda o valor do email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return name + ":" + email;
	}

	/*
	 * Retorna todas as semanas.
	 */
	public Week[] getWeeks() {
		return weeks;
	}

	/*
	 * Retorna a semana atual, que será a última posição do array
	 */
	public Week getWeekAtual() {
		return weeks[2];
	}

	/*
	 * Checa se é a semana atual, se não for inicializará uma nova.
	 */
	public void isActualWeek() {
		if (this.weeks[2].getNum() != Calendar.WEEK_OF_YEAR) {
			Week temp[] = new Week[3];
			System.arraycopy(this.weeks, 0, temp, 0, 3);

			this.weeks[0] = temp[1];
			this.weeks[0].decrementNum(1);

			this.weeks[1] = temp[2];
			this.weeks[1].decrementNum(1);

			this.weeks[2] = new Week();
		}
	}

}
