package com.example.seg2105_project.objects;

import java.util.ArrayList;

public class Employee extends User {
    private String address;
    private String phoneNumber;
    private String company;
    private ArrayList<Service> serviceList;
    private ArrayList<MyTime> workingHours;
    int waitingPeople;

    public Employee(String name, String email) {
        super(name, email);
        role = "Employee";
    }

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public ArrayList<MyTime> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(ArrayList<MyTime> workingHours) {
        this.workingHours = workingHours;
    }

    public int getWaitingPeople() {
        return waitingPeople;
    }

    public void setWaitingPeople(int waitingPeople) {
        this.waitingPeople = waitingPeople;
    }
}
