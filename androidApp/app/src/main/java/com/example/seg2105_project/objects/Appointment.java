package com.example.seg2105_project.objects;

import java.sql.Time;

public class Appointment {
    private String clinicName;
    private Service service;
    private MyTime time;

    public Appointment() {

    }

    public Appointment(String clinicName, Service service) {
        this.clinicName = clinicName;
        this.service = service;
        //this.time = time;
    }

    public Appointment(String clinicName, Service service, MyTime time) {
        this.clinicName = clinicName;
        this.service = service;
        this.time = time;
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

    public MyTime getTime() {
        return time;
    }

    public void setTime(MyTime time) {
        this.time = time;
    }
}
