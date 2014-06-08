package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Week {

	private int weekOftheyear;
	private List<ActivityTI> TiList;

	/*
	 * A new Week constructor
	 */
	public Week() {
		this.weekOftheyear = Calendar.WEEK_OF_YEAR;
		this.TiList = new ArrayList<ActivityTI>();
	}

	/** Method that returns the week of the year
	 * 
	 * @return
	 * 		- The number that represents the registered week of the year.
	 */
	public int getWeekOfYear() {
		return weekOftheyear;
	}

	/** 
	 * Add a new TI to the week or sums the already existing invested time with
	 * the new one. 
	 * 
	 * 
	 * @param actTI
	 * 		- TI to be registered or to have its time updated 
	 */
	public void addTI(ActivityTI actTI) {
		if (this.TiList.contains(actTI)) {
			int index = this.TiList.indexOf(actTI);
			this.TiList.get(index).addTime(actTI.getTime());
		} else {
			this.TiList.add(actTI);
		}
	}

	/** Decrements the current week by a specified number.
	 * 
	 * @param decrement
	 * 		-	A int that will be subtracted from weekOfTheYear
	 */
	public void decrementWeekOfTheYear(int decrement) {
		this.weekOftheyear -= decrement;
	}
	
	/** Method to return the invested time of an specific ActivityTI
	 *  of this week.
	 * 
	 * @param actTI
	 * 		- The ActivityTI to be searched
	 * @return
	 * 		- The total invested time on this Activity 
	 */
	public String timeOfTI(ActivityTI actTI){
		int saida = 0;
		if (this.TiList.contains(actTI)) {
			int index = this.TiList.indexOf(actTI);
			saida = this.TiList.get(index).getTime();
		}
		return String.valueOf(saida);	
	}
	
	
	/** Creates a rank based on the invested time of each TI of this week
	 * 
	 * @return
	 * 		A sorted List of ActivityTI based on the invested time of each one
	 */
	public String[] tiRank(){
		
		String[] saida = new String[TiList.size()];
		Collections.sort(TiList);
		for (int i = 0; i < saida.length; i++) {
			saida[i] = TiList.toString();
		}
		return saida;
		
	}
	
	@Override
	public String toString() {
		return TiList.toString();
	}
	

}
