package com.example.seg2105_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seg2105_project.R;
import com.example.seg2105_project.TimePickerFragment;
import com.example.seg2105_project.objects.Employee;
import com.example.seg2105_project.objects.MyTime;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ClinicActivity extends AppCompatActivity implements View.OnClickListener {
    public final String NODE_NAME_SERVICE = "ServiceForDemo";
    TextView tvWaitingPeople;
    TextView tvClinicName;
    //Button btnBookAppointment;
    Button btnCheckIn;
    ListView listViewWorkingHours;
    DatabaseReference databaseReferenceClinics;

    ArrayList<MyTime> timeList;
    Calendar date;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        tvWaitingPeople = findViewById(R.id.tvWaitingPeople);
        tvClinicName = findViewById(R.id.tvClinicName);
        //btnBookAppointment =findViewById(R.id.btnBookAppointment);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        listViewWorkingHours = findViewById(R.id.listViewWorkingHours);
        timeList = new ArrayList<>();
        //listViewWorkingHours.setAdapter();

        databaseReferenceClinics = FirebaseDatabase.getInstance().getReference()
                .child("Employee");
        //tvWaitingPeople.setText(databaseReferenceClinic.child("waitingPeople").get);
        databaseReferenceClinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("name").getValue().toString()
                            .equals(getIntent().getStringExtra("clinicName"))) {
                        Employee clinic = snapshot.getValue(Employee.class);
                        tvWaitingPeople.setText(clinic.getWaitingPeople());
                        tvClinicName.setText(clinic.getName());
                        //tvWaitingPeople.setText(snapshot.child("waitingPeople").getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//            case R.id.btnBookAppointment:
//                final Calendar currentDate = Calendar.getInstance();
//                date = Calendar.getInstance();
//                final int currentYear = date.get(Calendar.YEAR);
//                final int currentMonth = date.get(Calendar.MONTH);
//                final int currentDay = date.get(Calendar.DAY_OF_MONTH);
//                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        date.set(year, monthOfYear, dayOfMonth);
//                        month = date.get(Calendar.MONTH);
//                        day = date.get(Calendar.DAY_OF_MONTH);
//                        if (year < currentYear) {
//                            Toast.makeText(getApplicationContext(),
//                                    "Failed! Can not choose time before current time",
//                                    Toast.LENGTH_LONG).show();
//                            return;
//                        } else {
//                            if (month < currentMonth) {
//                                Toast.makeText(getApplicationContext(),
//                                        "Failed! Can not choose time before current time",
//                                        Toast.LENGTH_LONG).show();
//                                return;
//                            } else {
//                                if (day < currentDay) {
//                                    Toast.makeText(getApplicationContext(),
//                                            "Failed! Can not choose time before current time",
//                                            Toast.LENGTH_LONG).show();
//                                    return;
//                                }
//                            }
//                        }
//
//                        TimePickerFragment timePickerFragment2 = new TimePickerFragment();
//                        timePickerFragment2.setIsAdding(true, 1);
//                        timePickerFragment2.show(getFragmentManager(), "TIME");
//
//                        TimePickerFragment timePickerFragment = new TimePickerFragment();
//                        timePickerFragment.setIsAdding(true, 0);
//                        timePickerFragment.show(getFragmentManager(), "TIME");
//
//                        //currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE);
//                    }
//                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
//
//        }
    }
}
