package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	private List<ActivityTI> activities;
	
	public Day() {
		activities = new ArrayList<ActivityTI>();
	}
	
	public void addTI(ActivityTI activity){
		activities.add(activity);
	}

}
