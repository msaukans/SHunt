package com.codester.maris.shunt;



public class Task {

    int id;
    String taskName; //name of task
    int taskPoints;

    public Task(int Id, String Name, int Points){
        this.id = Id;
        this.taskName = Name;
        this.taskPoints = Points;
    }//end Task constructor

    public Task(String s) {
        taskName = s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return taskPoints;
    }

    public void setPoints(int points) {
        this.taskPoints = points;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setName(String name) {
        this.taskName = name;
    }

}
