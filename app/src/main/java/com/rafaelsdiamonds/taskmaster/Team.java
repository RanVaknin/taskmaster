package com.rafaelsdiamonds.taskmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Team {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String name;
    private LinkedList<Task> teamListOfTasks;

    public Team(String name) {
        this.name = name;
        this.teamListOfTasks = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Task> getTeamListOfTasks() {
        return teamListOfTasks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamListOfTasks(LinkedList<Task> teamListOfTasks) {
        this.teamListOfTasks = teamListOfTasks;
    }
}
