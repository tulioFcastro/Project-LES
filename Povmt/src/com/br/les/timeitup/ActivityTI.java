package com.br.les.timeitup;

import java.util.Comparator;

public class ActivityTI implements Comparable<ActivityTI>{

	private String name;
	private int time;
	private int priority;

	/** Creator 
	 * 
	 * @param name
	 * @param time
	 */
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

	public void addTime(int time) {
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

	@Override
	public int compareTo(ActivityTI actTi) {
		if(this.getTime()>actTi.getTime()){
			return -1;
		}else if (this.getTime()< actTi.getTime()){
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Name: "+ getName()+", tempo: "+getTime();
	}

}
