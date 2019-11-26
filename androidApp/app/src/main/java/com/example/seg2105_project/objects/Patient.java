package com.example.seg2105_project.objects;

import android.util.Pair;

import java.sql.Time;

public class Patient extends User {
    Pair<Employee, Time> appointment;

    public Patient(String name, String email) {
        super(name, email);
        role = "Patient";
    }
}
