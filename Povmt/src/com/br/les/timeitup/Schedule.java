package com.br.les.timeitup;

import java.util.List;

public class Schedule {

	private Long id;
	private User user;
	private List<Week> weeks;
	
	public Schedule(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}
	
	public void addWeek(Week week){
		weeks.add(week);
	}

}
