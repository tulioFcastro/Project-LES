package com.br.les.timeitup;

import java.util.List;

public class Week {
	
	private String name;
	List<Activity> activities;
	
	public Week(String name) {
		this.name = name;
	}
	
	public void addActivities(Activity activity){
		activities.add(activity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public String calculateWeekTime(){
		for (Activity ac : activities) {
			//ToDo
		}
		return "";
	}
	
}
