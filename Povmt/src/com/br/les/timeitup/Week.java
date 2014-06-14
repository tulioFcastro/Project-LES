package com.br.les.timeitup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Week {

	private int weekOftheyear;
	private final List<ActivityTI> TiList;

	/*
	 * A new Week constructor
	 */
	public Week() {
		weekOftheyear = Calendar.WEEK_OF_YEAR;
		TiList = new ArrayList<ActivityTI>();
	}

	/**
	 * Method that returns the week of the year
	 * 
	 * @return - The number that represents the registered week of the year.
	 */
	public int getWeekOfYear() {
		return weekOftheyear;
	}

	/**
	 * Add a new TI to the week or sums the already existing invested time with
	 * the new one.
	 * 
	 * @param actTI
	 *            - TI to be registered or to have its time updated
	 */
	public void addTI(ActivityTI actTI) {
		if (TiList.contains(actTI)) {
			int index = TiList.indexOf(actTI);
			TiList.get(index).addTime(actTI.getHour(), actTI.getMinute());
		} else {
			TiList.add(actTI);
		}
	}

	/**
	 * Decrements the current week by a specified number.
	 * 
	 * @param decrement
	 *            - A int that will be subtracted from weekOfTheYear
	 */
	public void decrementWeekOfTheYear(int decrement) {
		weekOftheyear -= decrement;
	}

	/**
	 * Method to return the invested time of an specific ActivityTI of this
	 * week.
	 * 
	 * @param actTI
	 *            - The ActivityTI to be searched
	 * @return - The total invested time on this Activity
	 */
	public String timeOfTI(ActivityTI actTI) {
		float saida = 0;
		if (TiList.contains(actTI)) {
			int index = TiList.indexOf(actTI);
			saida = TiList.get(index).getTime();
		}
		return String.valueOf(saida);
	}

	/**
	 * Creates a rank based on the invested time of each TI of this week
	 * 
	 * @return A sorted List of ActivityTI based on the invested time of each
	 *         one
	 */
	public String[] tiRank() {

		String[] out;
		if (TiList.size() == 0) {
			out = new String[] { "N�o h� atividade cadastrada" };
		} else {
			out = new String[TiList.size()];
			Collections.sort(TiList);
			for (int i = 0; i < out.length; i++) {
				out[i] = TiList.get(i).toString();
				DecimalFormat df = new DecimalFormat("0.00");

				out[i] += ", proportion: "
						+ df.format(proportion(TiList.get(i))) + "%";
			}
		}

		return out;

	}

	/**
	 * Get the proportion of the activityTI
	 * 
	 * @return A float with the proportion
	 */
	private float proportion(ActivityTI activityTI) {
		float total = 0;

		for (ActivityTI actTi : TiList) {
			total += actTi.getTime();
		}
		System.out.println(total + "");
		return activityTI.getTime() / total * 100;

	}

	@Override
	public String toString() {
		return TiList.toString();
	}

}
