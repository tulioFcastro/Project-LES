package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Week {

	private int num;
	private List<ActivityTI> TiList;

	/*
	 * Construtor da classe
	 */
	public Week() {
		this.num = Calendar.WEEK_OF_YEAR;
		this.TiList = new ArrayList<ActivityTI>();
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
		if (this.TiList.contains(actTI)) {
			int index = this.TiList.indexOf(actTI);
			this.TiList.get(index).addTime(actTI.getTime());
		} else {
			this.TiList.add(actTI);
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
	
	
	//Retorna o tempo da ti SOMADO
	public String timeOfTI(ActivityTI actTI){
		int saida = 0;
		if (this.TiList.contains(actTI)) {
			int index = this.TiList.indexOf(actTI);
			saida = this.TiList.get(index).getTime();
		}
		return String.valueOf(saida);	
	}
	
	//Retorna as tis ordenadas
	public List<ActivityTI> tiRank(){
		
		Collections.sort(TiList);
		return TiList;
		
	}
	

}
