package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;

public class ManageAvailabilityActivity extends AppCompatActivity implements View.OnClickListener {
    ListView mListView;
    ArrayList<MyTime> timeList;
    Button btnAddAvailability;
    String timeInterval;
    Calendar date;
    int month;
    int day;
    //public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_availability);

        btnAddAvailability = findViewById(R.id.btnAddAvailability);
        btnAddAvailability.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listView);
        timeList = new ArrayList<>();

        TimeListAdapter adapter = new TimeListAdapter(this,  R.layout.adapter_view_layout, timeList, this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimePickerFragment timePickerFragment2 = new TimePickerFragment();
                timePickerFragment2.setPosition(position);
                timePickerFragment2.setIsAdding(false, 1);
                timePickerFragment2.show(getFragmentManager(), "TIME");

                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setPosition(position);
                timePickerFragment.setIsAdding(false, 0);
                timePickerFragment.show(getFragmentManager(), "TIME");

//                final int position1 = position;
//
//                //final int position1 = position;
//                final Calendar currentDate = Calendar.getInstance();
//                date = Calendar.getInstance();
//                new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        date.set(year, monthOfYear, dayOfMonth);
//                        month = date.get(Calendar.MONTH);
//                        day = date.get(Calendar.DAY_OF_MONTH);
//
////                        TimePickerFragment timePickerFragment = new TimePickerFragment();
////                        //timePickerFragment.setPosition(position1);
////                        timePickerFragment.setIsAdding(false, 0);
////                        timePickerFragment.show(getFragmentManager(), "TIME");
////
////                        TimePickerFragment timePickerFragment2 = new TimePickerFragment();
////                        //timePickerFragment2.setPosition(position1);
////                        timePickerFragment2.setIsAdding(false, 1);
////                        timePickerFragment2.show(getFragmentManager(), "TIME");
//
//                        //currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE);
//                    }
//                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();


            }
        });
    }

    @Override
    public void onClick(View v) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                month = date.get(Calendar.MONTH);
                day = date.get(Calendar.DAY_OF_MONTH);

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
        TimeListAdapter adapter = new TimeListAdapter(this, R.layout.adapter_view_layout, timeList,this);
        mListView.setAdapter(adapter);
    }

    public void applyTime0(int position, int hour, int minute) {
        timeInterval = hour + ":" + minute;
    }

    public void applyTime(int position, int hour, int minute) {
        timeInterval += " - " + hour + ":" + minute;
        MyTime myTime = new MyTime(month, day, timeInterval);
        timeList.set(position, myTime);
        TimeListAdapter adapter = new TimeListAdapter(this, R.layout.adapter_view_layout, timeList,this);
        mListView.setAdapter(adapter);
    }

}
