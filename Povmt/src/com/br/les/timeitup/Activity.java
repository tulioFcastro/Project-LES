package com.br.les.timeitup;

import java.util.Calendar;

public class Activity {
	
	private String name;
	private String start_time;
	private String end_time;
	private int priority;
	
	public Activity(String name, String start_time, String end_time) {
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String calculateActivityTime(){
		//ToDo
		return "";
	}
	

}
