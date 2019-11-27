package com.example.seg2105_project.objects;

public class MyTime {
    int month;
    int day;
    String timeInterval;

    public MyTime() {

    }

    public MyTime(int month, int day,
                  String timeInterval){
        this.month = month;
        this.day = day;
        this.timeInterval = timeInterval;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
