package com.example.seg2105_project.objects;

import java.util.ArrayList;

public class Employee extends User {
    private String address;
    private String phoneNumber;
    private String company;
    private boolean isLicensed;
    private String decription;
    private ArrayList<Service> serviceList;
    private ArrayList<MyTime> workingHours;
    int waitingPeople;

    public Employee() {

    }

    public Employee(String name, String email) {
        super(name, email);
    }

    public Employee(String name, String email, String address, String phoneNumber,
                    String company, boolean isLicensed, String decription,
                    ArrayList<Service> serviceList, ArrayList<MyTime> workingHours, int waitingPeople) {
        super(name, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.isLicensed = isLicensed;
        this.decription = decription;
        this.serviceList = serviceList;
        this.workingHours = workingHours;
        this.waitingPeople = waitingPeople;
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
