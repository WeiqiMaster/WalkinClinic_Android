package com.example.seg2105_project;

import java.util.ArrayList;

public class Employee extends User {
    private String address;
    private String phoneNumber;
    private String company;
    private ArrayList<Service> serviceList;

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
}
