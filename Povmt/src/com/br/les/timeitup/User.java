
package com.br.les.timeitup;

import java.util.Calendar;

public class User {

    public User() {
        weeks = new Week[3];
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

        weeks = new Week[3];
    }

    private final Week[] weeks;
    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + ":" + email;
    }

    public Week[] getWeeks() {
        return weeks;
    }

    public Week getWeekAtual() {
        return weeks[0];
    }

    private boolean actualWeekIsEmpty() {

        if (weeks[0] != null) {
            return true;
        }
        return false;

    }

    public void createActualWeek() {

        int weekNum = Calendar.WEEK_OF_YEAR;
        Week week = new Week(weekNum);
        if (actualWeekIsEmpty()) {
            weeks[0] = week;
        } else {
            movingWeek();
            weeks[0] = week;
        }

    }

    private void movingWeek() {
        weeks[2] = weeks[1];
        weeks[1] = null;
        weeks[1] = weeks[0];
        weeks[0] = null;
    }

    public boolean isActualWeek() {
        int weekNum = Calendar.WEEK_OF_YEAR;
        if (weekNum == weeks[0].getNum()) {
            return true;
        } else {
            return false;
        }
    }
}
