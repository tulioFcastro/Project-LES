package com.br.les.timeitup;

import java.util.List;

public class Week {
	
	private String name;
	List<Day> activities;
	
	public Week(String name) {
		this.name = name;
	}
	
	public void addDay(Day dia){
		activities.add(dia);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Day> getDays() {
		return activities;
	}

	public void setActivities(List<Day> activities) {
		this.activities = activities;
	}
	
	public String calculateWeekTime(){
		for (Day ac : activities) {
			//ToDo
		}
		return "";
	}
	
}
