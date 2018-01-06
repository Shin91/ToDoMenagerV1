package com.example.shin.todomenagerv1;


public class Tasks {

    private int taskId;
    private String taskTitle;
    private String taskDesc;


    public String getTask() {

        return taskTitle;
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
