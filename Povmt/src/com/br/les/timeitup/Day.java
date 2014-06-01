package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	private String name;
	
	private List<ActivityTI> activities;
	
	public Day(String name) {
		this.name = name;
		activities = new ArrayList<ActivityTI>();
	}
	
	public void addTI(ActivityTI activity){
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
	
}
