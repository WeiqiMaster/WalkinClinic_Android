package com.example.seg2105_project.objects;

import java.sql.Time;

public class Appointment {
    private String clinicName;
    private Service service;
    private Time time;

    public Appointment() {

    }

    public Appointment(String clinicName, Service service) {
        this.clinicName = clinicName;
        this.service = service;
        //this.time = time;
    }

    public String getClinic() {
        return clinicName;
    }

    public void setClinic(String clinicName) {
        this.clinicName = clinicName;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
