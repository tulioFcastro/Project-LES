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
	
	public int getTime(){
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String calculateActivityTime(){
		//ToDo
		return "";
	}
	

}
