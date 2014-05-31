package com.br.les.timeitup;

import java.util.List;

public class Week {
	
	private String name;
	List<ActivityTI> activities;
	
	public Week(String name) {
		this.name = name;
	}
	
	public void addActivities(ActivityTI activity){
		activities.add(activity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ActivityTI> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityTI> activities) {
		this.activities = activities;
	}
	
	public String calculateWeekTime(){
		for (ActivityTI ac : activities) {
			//ToDo
		}
		return "";
	}
	
}
