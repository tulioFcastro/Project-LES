
package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.List;

public class User {

    public User() {
        this.weeks = new ArrayList<Week>();
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.weeks = new ArrayList<Week>();
    }

    private List<Week> weeks;
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

    public List<Week> getWeeks() {
        return weeks;
    }
}
