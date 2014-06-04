package com.br.les.timeitup;

import java.util.List;

public class Week {

    private String name;
    List<Day> days;

    public Week(String name) {
        this.name = name;
    }

    public void addDay(Day dia) {
        days.add(dia);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        days = days;
    }

    public String calculateWeekTime() {
        for (Day ac : days) {
            // ToDo
        }
        return "";
    }

}
