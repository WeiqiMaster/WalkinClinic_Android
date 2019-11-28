package com.example.seg2105_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.seg2105_project.dialog.DialogEmployeeAddService;
import com.example.seg2105_project.R;
import com.example.seg2105_project.adapters.TimeListAdapter;
import com.example.seg2105_project.dialog.OneTimeTimePicker;
import com.example.seg2105_project.dialog.TimePickerFragment;
import com.example.seg2105_project.objects.Appointment;
import com.example.seg2105_project.objects.Employee;
import com.example.seg2105_project.objects.MyTime;
import com.example.seg2105_project.objects.Patient;
import com.example.seg2105_project.objects.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class ClinicActivity extends AppCompatActivity implements View.OnClickListener {
    public final String NODE_NAME_SERVICE = "ServiceForDemo";
    TextView tvWaitingPeople;
    TextView tvClinicName;
    TextView tvHaveAppointment;
    TextView tvRating;
    Button btnCancelAppointment;
    Button btnCheckIn;
    Button btnRate;
    ListView listViewWorkingHours;

    DatabaseReference databaseClinic;
    DatabaseReference databasePatient;

    ArrayList<Service> serviceList;
    ArrayList<String> serviceNameList;
    ArrayList<MyTime> timeList;
    String email;

    int servicePosition = -1;
    Appointment appointment;
    Patient loginPatient;
    //boolean[] checkedServices = new boolean[serviceNameList.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        tvWaitingPeople = findViewById(R.id.tvWaitingPeople);
        tvClinicName = findViewById(R.id.tvClinicName);
        tvHaveAppointment = findViewById(R.id.tvHaveAppointment);
        tvRating = findViewById(R.id.tvRating);
        btnCancelAppointment =findViewById(R.id.btnCancelAppointment);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnRate = findViewById(R.id.btnRate);
        listViewWorkingHours = findViewById(R.id.listViewWorkingHours);
        timeList = new ArrayList<>();
        //listViewWorkingHours.setAdapter();

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        email = fbUser.getEmail().replace(".", "");
        databasePatient = FirebaseDatabase.getInstance().getReference().child("Patient").child(email).child("appointment");
        databaseClinic = FirebaseDatabase.getInstance().getReference().child("Employee");
        final String clinicName = getIntent().getStringExtra("clinicName");


        databasePatient.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child("time");
                tvHaveAppointment.setText("You have an appointment at this clinic on "
                        + dataSnapshot.child("day").getValue() + " / " + dataSnapshot.child("month").getValue()
                        + " at " + dataSnapshot.child("hours").getValue() + " : " + dataSnapshot.child("minute").getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseClinic.child("rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) {
                    tvRating.setText("No Rating for this clinic");
                } else {
                    tvRating.setText("Rating: " + dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //tvWaitingPeople.setText(databaseReferenceClinic.child("waitingPeople").get);
        databaseClinic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("name").getValue().toString()
                            .equals(clinicName)) {
                        Employee clinic = snapshot.getValue(Employee.class);
                        tvWaitingPeople.setText(String.valueOf(clinic.getWaitingPeople()));
                        tvClinicName.setText(clinic.getName());
                        //tvWaitingPeople.setText(snapshot.child("waitingPeople").getValue().toString());
                        serviceList = new ArrayList<>();
                        serviceNameList = new ArrayList<>();
                        for (DataSnapshot snapshot1 : snapshot.child("serviceList").getChildren()) {
                            serviceList.add(snapshot1.getValue(Service.class));
                            serviceNameList.add(snapshot1.child("name").getValue().toString());
                        }
                        for (DataSnapshot snapshot2 : snapshot.child("workingHours").getChildren()) {
                            timeList.add(snapshot2.getValue(MyTime.class));
                        }
                    }
                }
                TimeListAdapter adapter = new TimeListAdapter(getApplicationContext(),  R.layout.adapter_view_layout, timeList);
                listViewWorkingHours.setAdapter(adapter);
                listViewWorkingHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ClinicActivity.this);
                        builder.setTitle("Choose a Service");
                        String[] servicesNameArray = serviceNameList.toArray(new String[serviceNameList.size()]);

                        builder.setSingleChoiceItems(servicesNameArray, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                servicePosition = which;
                            }
                        });
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appointment = new Appointment(getIntent().getStringExtra("clinicName"),
                                        serviceList.get(servicePosition));
                                OneTimeTimePicker oneTimeTimePicker = new OneTimeTimePicker();
                                oneTimeTimePicker.setPosition(position);
                                oneTimeTimePicker.show(getFragmentManager(), "TIME");

                            }
                        });
                        builder.setNegativeButton("Cancel", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




    }

    // check in
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancelAppointment:
                break;
            case  R.id.btnRate:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final RatingBar ratingBar = new RatingBar(this);
                ratingBar.setNumStars(5);
                builder.setTitle("Rate This Clinic");
                builder.setView(ratingBar);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseClinic.child("rating").setValue(ratingBar.getRating());
                    }
                });
        }
    }

    public void applyPickedTime (int position, int hourOfDay, int minute) {
        Toast.makeText(getApplicationContext(),
                Integer.toString(hourOfDay) + Integer.toString(minute),
                Toast.LENGTH_LONG).show();
        MyTime time = new MyTime(timeList.get(position).getMonth(),
                timeList.get(position).getDay(), hourOfDay, minute);
        appointment.setTime(time);
        databasePatient.setValue(appointment);
//        Toast.makeText(getApplicationContext(),
//                "Successfully book the appointment!",
//                Toast.LENGTH_LONG).show();
    }

}
