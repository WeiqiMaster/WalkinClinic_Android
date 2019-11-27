package com.example.seg2105_project.objects;

public class Service {
    private String name;
    private String roleOfPerson;
    private int index;

    public Service(String name, String roleOfPerson) {
        this.name = name;
        this.roleOfPerson = roleOfPerson;

    }

    public Service() {

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
