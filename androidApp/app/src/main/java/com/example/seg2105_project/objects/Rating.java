package com.example.seg2105_project.objects;

public class Rating {
    private  Patient patient;
    private float rating;

    public Rating(Patient patient, float rating) {
        this.patient = patient;
        this.rating = rating;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
