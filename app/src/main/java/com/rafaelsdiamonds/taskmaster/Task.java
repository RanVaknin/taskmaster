package com.rafaelsdiamonds.taskmaster;


public class Task {
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
}
