package com.example.seg2105_project.objects;

public class Service {
    private String name;
    //int hourlyRate;
    private String roleOfPerson;
    private double rate;

    public Service(String name, String roleOfPerson) {
        this.name = name;
        this.roleOfPerson = roleOfPerson;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleOfPerson() {
        return roleOfPerson;
    }

    public void setRoleOfPerson(String roleOfPerson) {
        this.roleOfPerson = roleOfPerson;
    }
}
