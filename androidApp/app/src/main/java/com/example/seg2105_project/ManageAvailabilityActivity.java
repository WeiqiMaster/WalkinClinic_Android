package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;

import java.util.Calendar;

public class ManageAvailabilityActivity extends AppCompatActivity {

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int hour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_availability);
    }
}
