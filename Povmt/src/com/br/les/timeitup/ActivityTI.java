package com.br.les.timeitup;

import java.util.Comparator;

public class ActivityTI implements Comparable<ActivityTI>{

	private String name;
	private int time;
	private int priority;

	/** Creator for a new ActivityTI 
	 * 
	 * @param name
	 * 		- The name of this Activity
	 * @param time
	 * 		- The time spent on this Activity
	 */
	public ActivityTI(String name, int time) {
		this.name = name;
		this.time = time;
	}

	/** Method get for the Activity`s name
	 * 
	 * @return
	 * 		- The name of the ActivityTI
	 */
	public String getName() {
		return name;
	}
	
	/** Method get for the Activity`s time
	 * 
	 * @return
	 * 		- The time spent on the ActivityTI
	 */
	public int getTime() {
		return time;
	}

	/** Method set for the Activity`s name
	 * 
	 * @param name
	 * 		- The new name for this ActivityTI
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Method set for the Activity`s time
	 * 
	 * @param time
	 * 		- The new time for this ActivityTI
	 */	
	public void addTime(int time) {
		this.time += time;
	}

	/** Method get for the Activity's priority
	 * 
	 * @return
	 * 		- int that represents this activity priority
	 */
	public int getPriority() {
		return priority;
	}

	/** Method set for the Activity's priority
	 * 
	 * @param priority
	 * 		- The new priority of this ActivityTI
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Method compare to, uses the time of one's Activity for comparison
	 * 
	 * @return
	 * 		- 0 if both have the same time, 1 if this activity spent more time and -1 if not.
	 */
	@Override
	public int compareTo(ActivityTI actTi) {
		if(this.getTime()>actTi.getTime()){
			return -1;
		}else if (this.getTime()< actTi.getTime()){
			return 1;
		}
		return 0;
	}
	
	/**
	 * @return String in the format <Activity_name> : <Activity_time>
	 */
	@Override
	public String toString() {
		return "Name: "+ getName()+", tempo: "+getTime();
	}

}
