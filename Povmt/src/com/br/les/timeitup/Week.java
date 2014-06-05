package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Week {

	private int num;
	private List<ActivityTI> mapDiaTi;

	/*
	 * Construtor da classe
	 */
	public Week() {
		this.num = Calendar.WEEK_OF_YEAR;
		this.mapDiaTi = new ArrayList<ActivityTI>();
	}

	/*
	 * Retorna o número da semana no ano atual do sistema.
	 */
	public int getNum() {
		return num;
	}

	/*
	 * Adiciona TI à semana ou soma sua quantidade de horas se já tiver sido
	 * adicionada anteriormente
	 * 
	 * @author Grupo 3 - LES Adiciona TI na semana
	 * 
	 * @param TI a ser adicionado ou somada a quantidade de horas
	 */
	public void addTI(ActivityTI actTI) {
		if (this.mapDiaTi.contains(actTI)) {
			int index = this.mapDiaTi.indexOf(actTI);
			this.mapDiaTi.get(index).setTime(actTI.getTime());
		} else {
			this.mapDiaTi.add(actTI);
		}
	}

	/*
	 * Decrementa o valor de num.
	 * 
	 * @param num valor a ser decrementado
	 */
	public void decrementNum(int num) {
		this.num -= num;
	}

}
