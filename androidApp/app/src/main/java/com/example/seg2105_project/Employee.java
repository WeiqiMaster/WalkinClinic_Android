package com.example.seg2105_project;

public class Employee extends User {
    private String address;
    private String phoneNumber;
    private String company;

    public Employee(String name, String email) {
        super(name, email);
        role = "Employee";
    }
}
