package com.example.taskmaster;

public class TaskModel {
// title, a body, and a state
    public String title;
    public String body;
    public String state;

    public TaskModel() {
    }

    public TaskModel(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
