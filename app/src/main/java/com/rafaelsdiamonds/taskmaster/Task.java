package com.rafaelsdiamonds.taskmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey
    private long id;
    private String title;
    private String body;
    private String state;




    public Task(String title, String body) {
        this.title = title;
        this.body = body;
        this.state = "new";
    }


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setState(String state) {
        if(state.toLowerCase().equals("new") || state.toLowerCase().equals("in progress")
        || state.toLowerCase().equals("complete") || state.toLowerCase().equals("assigned")){
        this.state = state;

        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
