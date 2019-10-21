package com.example.seg2105_project;

public class Patient extends User {

    public Patient(String name, String email) {
        super(name, email);
        role = "Patient";
    }
}
