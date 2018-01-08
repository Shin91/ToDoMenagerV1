package com.example.shin.todomenagerv1;


public class Tasks {

    private int taskId;
    private String taskTitle;
    private String taskDesc;
    private int isDone = 0;



    public void setDone(int done) {

        isDone = done;
    }

    public int getDone() {
        return isDone;
    }

    public String getTask() {

        return taskTitle;
    }

    public Tasks(int taskId, int isDone) {
        this.taskId = taskId;
        this.isDone = isDone;
    }

    public Tasks(int taskId, String taskTitle, String taskDesc, int isDone) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.isDone = isDone;
    }

    public void setTask(String task) {
        this.taskTitle = task;
    }


    public String getDescription() {
        return taskDesc;
    }


    public void setDescription(String description) {
        this.taskDesc = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Tasks(int taskId, String taskTitle, int isDone) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.isDone = isDone;
    }

    public Tasks() {
    }

    public Tasks(int taskId) {
        this.taskId = taskId;
    }

    public Tasks(String title) {

        this.taskTitle = title;
    }

    public Tasks(int id, String title) {

        this.taskId = id;
        this.taskTitle = title;
    }

    public Tasks(int id, String title, String desc) {

        this.taskId = id;
        this.taskTitle = title;
        this.taskDesc = desc;
    }


}
