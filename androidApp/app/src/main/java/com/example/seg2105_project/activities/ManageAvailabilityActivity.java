package com.example.seg2105_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;


import com.example.seg2105_project.dialog.DialogEditAvailability;
import com.example.seg2105_project.objects.Employee;
import com.example.seg2105_project.objects.MyTime;
import com.example.seg2105_project.R;
import com.example.seg2105_project.dialog.TimePickerFragment;
import com.example.seg2105_project.adapters.TimeListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ManageAvailabilityActivity extends AppCompatActivity implements View.OnClickListener, DialogEditAvailability.DialogUpdateAvailabilityListener {
    ListView mListView;
    ArrayList<MyTime> timeList;
    Button btnAddAvailability;
    String timeInterval;
    Calendar date;
    int month;
    int day;
    Employee currentClinic;
    DatabaseReference databaseClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_availability);

        btnAddAvailability = findViewById(R.id.btnAddAvailability);
        btnAddAvailability.setOnClickListener(this);

        mListView = findViewById(R.id.listView);

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = fbUser.getEmail().replace(".", "");
        databaseClinic = FirebaseDatabase.getInstance().getReference().child("Employee").child(email);
        timeList = new ArrayList<>();


        TimeListAdapter adapter = new TimeListAdapter(this,  R.layout.adapter_view_layout, timeList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogEditAvailability dialogEditAvailability = new DialogEditAvailability();
                dialogEditAvailability.setPosition(position);
                dialogEditAvailability.show(getSupportFragmentManager(),"Update Availability");
            }
        });
    }

    @Override
    public void onClick(View v) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        final int currentYear = date.get(Calendar.YEAR);
        final int currentMonth = date.get(Calendar.MONTH);
        final int currentDay = date.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                month = date.get(Calendar.MONTH);
                day = date.get(Calendar.DAY_OF_MONTH);
                if (year < currentYear) {
                    Toast.makeText(getApplicationContext(),
                            "Failed! Can not choose time before current time",
                            Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (month < currentMonth) {
                        Toast.makeText(getApplicationContext(),
                                "Failed! Can not choose time before current time",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        if (day < currentDay) {
                            Toast.makeText(getApplicationContext(),
                                    "Failed! Can not choose time before current time",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }

                TimePickerFragment timePickerFragment2 = new TimePickerFragment();
                timePickerFragment2.setIsAdding(true, 1);
                timePickerFragment2.show(getFragmentManager(), "TIME");

                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setIsAdding(true, 0);
                timePickerFragment.show(getFragmentManager(), "TIME");

                //currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE);
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();


    }

    // add working hours
    public void addTime0(int hour, int minute) {
        Toast.makeText(getApplicationContext(),
                Integer.toString(hour) + ":" + Integer.toString(minute),
                Toast.LENGTH_LONG).show();
        timeInterval = hour + ":" + minute;
    }

    public void addTime(int hour, int minute) {
        String timeInterval2 = timeInterval + " - " + hour + ":" + minute;
        MyTime myTime = new MyTime(month, day, timeInterval2);
        timeList.add(myTime);
        databaseClinic.child("workingHours").setValue(timeList);
        TimeListAdapter adapter = new TimeListAdapter(this, R.layout.adapter_view_layout, timeList);
        mListView.setAdapter(adapter);
    }

    // Modify working hours
    public void applyTime0(int position, int hour, int minute) {
        timeInterval = hour + ":" + minute;
    }

    public void applyTime(int position, int hour, int minute) {
        timeInterval += " - " + hour + ":" + minute;
        MyTime myTime = new MyTime(month, day, timeInterval);
        timeList.set(position, myTime);
        databaseClinic.child("workingHours").setValue(timeList);
        TimeListAdapter adapter = new TimeListAdapter(this, R.layout.adapter_view_layout, timeList);
        mListView.setAdapter(adapter);
    }

    @Override
    public void apply(final int position, boolean isDelete) {
        if (isDelete) {
            timeList.remove(position);
            databaseClinic.child("workingHours").setValue(timeList);
            TimeListAdapter adapter = new TimeListAdapter(this, R.layout.adapter_view_layout, timeList);
            mListView.setAdapter(adapter);
        } else {
            final Calendar currentDate = Calendar.getInstance();
            date = Calendar.getInstance();
            final int currentYear = date.get(Calendar.YEAR);
            final int currentMonth = date.get(Calendar.MONTH);
            final int currentDay = date.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    date.set(year, monthOfYear, dayOfMonth);
                    month = date.get(Calendar.MONTH);
                    day = date.get(Calendar.DAY_OF_MONTH);
                    if (year < currentYear) {
                        Toast.makeText(getApplicationContext(),
                                "Failed! Can not choose time before current time",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        if (month < currentMonth) {
                            Toast.makeText(getApplicationContext(),
                                    "Failed! Can not choose time before current time",
                                    Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            if (day < currentDay) {
                                Toast.makeText(getApplicationContext(),
                                        "Failed! Can not choose time before current time",
                                        Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    TimePickerFragment timePickerFragment2 = new TimePickerFragment();
                    timePickerFragment2.setPosition(position);
                    timePickerFragment2.setIsAdding(false, 1);
                    timePickerFragment2.show(getFragmentManager(), "TIME");

                    TimePickerFragment timePickerFragment = new TimePickerFragment();
                    timePickerFragment.setPosition(position);
                    timePickerFragment.setIsAdding(false, 0);
                    timePickerFragment.show(getFragmentManager(), "TIME");

                    //currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE);
                }
            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

        }
    }
}
