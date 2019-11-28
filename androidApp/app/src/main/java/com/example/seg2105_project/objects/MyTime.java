package com.example.seg2105_project.objects;

public class MyTime {
    int month;
    int day;
    int hours;
    int minute;
    String timeInterval;

    public MyTime() {

    }

    public MyTime(int month, int day,
                  String timeInterval){
        this.month = month;
        this.day = day;
        this.timeInterval = timeInterval;
    }

    public MyTime(int month, int day, int hours, int minute, String timeInterval) {
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minute = minute;
        this.timeInterval = timeInterval;
    }

    public MyTime(int month, int day, int hours, int minute) {
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minute = minute;
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
