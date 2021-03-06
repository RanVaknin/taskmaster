package com.rafaelsdiamonds.taskmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String title;
    private String body;
    private String state;
    private String image;




    public Task(String title, String body, String image) {
        this.title = title;
        this.body = body;
        this.state = "new";
        this.image = image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getImage() {
        return image;
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

    public void setId(int id) {
        this.id = id;
    }
}
