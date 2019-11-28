package com.example.seg2105_project.objects;

import java.util.ArrayList;

public class Employee extends User {
    private String address;
    private String phoneNumber;
    private String company;
    private boolean isLicensed;
    private String description;
    private ArrayList<Service> serviceList;
    private ArrayList<MyTime> workingHours;
    int waitingPeople;
    private float rating;
    private ArrayList<Rating> ratings;

    private int totalRatingPeople;

    public Employee() {
        address = "";
        phoneNumber = "";
        company = "";
        isLicensed = false;
        description = "";
    }

    public Employee(String name, String email) {
        super(name, email);
    }

    public Employee(String name, String email, String address, String phoneNumber, String company, boolean isLicensed, String description, ArrayList<Service> serviceList, ArrayList<MyTime> workingHours, int waitingPeople, float rating) {
        super(name, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.isLicensed = isLicensed;
        this.description = description;
        this.serviceList = serviceList;
        this.workingHours = workingHours;
        this.waitingPeople = waitingPeople;
        this.rating = rating;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isLicensed() {
        return isLicensed;
    }

    public void setLicensed(boolean licensed) {
        isLicensed = licensed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRatingPeople() {
        return totalRatingPeople;
    }

    public void setTotalRatingPeople(int totalRatingPeople) {
        this.totalRatingPeople = totalRatingPeople;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
}
