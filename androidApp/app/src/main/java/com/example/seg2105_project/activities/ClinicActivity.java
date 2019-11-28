package com.example.seg2105_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seg2105_project.R;
import com.example.seg2105_project.adapters.TimeListAdapter;
import com.example.seg2105_project.dialog.OneTimeTimePicker;
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

import java.util.ArrayList;

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
    DatabaseReference databasePatientAppointment;

    ArrayList<Service> serviceList;
    ArrayList<String> serviceNameList;
    ArrayList<MyTime> timeList;
    String email;
    String clinicName;

    int servicePosition = -1;
    Appointment appointment;
    Patient loginPatient;

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
        btnRate.setOnClickListener(this);
        btnCancelAppointment.setOnClickListener(this);
        btnCheckIn.setOnClickListener(this);
        listViewWorkingHours = findViewById(R.id.listViewWorkingHours);
        timeList = new ArrayList<>();
        //listViewWorkingHours.setAdapter();

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        email = fbUser.getEmail().replace(".", "");
        databasePatientAppointment = FirebaseDatabase.getInstance().getReference().child("Patient").child(email).child("appointment");
        databaseClinic = FirebaseDatabase.getInstance().getReference().child("Employee");
        clinicName = getIntent().getStringExtra("clinicName");

        databasePatientAppointment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("clinic").getValue() == null) {
                    tvHaveAppointment.setText("You have no appointment with this clinic.");
                    return;
                }
                if (dataSnapshot.child("clinic").getValue().toString().equals(clinicName)) {
                    dataSnapshot = dataSnapshot.child("time");
                    String appointmentText = "You have an appointment at this clinic on "
                            + dataSnapshot.child("day").getValue() + " / " + dataSnapshot.child("month").getValue()
                            + " at " + dataSnapshot.child("hours").getValue() + " : " + dataSnapshot.child("minute").getValue() + ".";
                    tvHaveAppointment.setText(appointmentText);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseClinic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("name").getValue().toString()
                            .equals(clinicName)) {
                        Employee clinic = snapshot.getValue(Employee.class);
                        String waitingText = "The number of people waiting: "
                                + String.valueOf(clinic.getWaitingPeople())
                                + ". Expected Waiting Time: " + Integer.toString(clinic.getWaitingPeople() * 15);
                        tvWaitingPeople.setText(waitingText);
                        tvClinicName.setText(clinic.getName().toUpperCase());
                        if (snapshot.child("rating").getValue() == null) {
                            tvRating.setText("No Rating for this clinic");
                        } else {
                            tvRating.setText("Rating: " + snapshot.child("rating").getValue().toString());
                        }
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
                databasePatientAppointment.removeValue();
                break;
            case  R.id.btnRate:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final RatingBar ratingBar = new RatingBar(this);
                //ratingBar.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT; // LayoutParams: android.view.ViewGroup.LayoutParams
                //ratingBar.requestLayout();
                ratingBar.setMax(5);
                ratingBar.setNumStars(5);
                //ratingBar.setStepSize(0.1f);
                ratingBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                LinearLayout parent = new LinearLayout(this);
                parent.setGravity(Gravity.CENTER);
                parent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                parent.addView(ratingBar);

                //builder.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                //    ViewGroup.LayoutParams.WRAP_CONTENT));
                builder.setTitle("Rate This Clinic");
                builder.setView(parent);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseClinic.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    if (snapshot.child("name").getValue().toString().equals(clinicName)) {
                                        email = snapshot.child("email").getValue().toString().replace(".", "");;
                                    }
                                }
                                databaseClinic.child(email).child("rating").setValue(ratingBar.getRating());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.btnCheckIn:
                Toast.makeText(getApplicationContext(),
                        "You have checked in.",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void applyPickedTime (int position, int hourOfDay, int minute) {
        // time restriction
        String[] hoursMinute= timeList.get(position).getTimeInterval().split(" - ");
        String first = hoursMinute[0];
        String second = hoursMinute[1];
        if (hourOfDay < Integer.parseInt(first.split(":")[0])
                || (hourOfDay == Integer.parseInt(first.split(":")[0]) && minute < Integer.parseInt(first.split(":")[1]))) {
            Toast.makeText(getApplicationContext(),
                    "Failed! Can't choose earlier time",
                    Toast.LENGTH_LONG).show();
            return;
        } else if (hourOfDay > Integer.parseInt(second.split(":")[0])
                || (hourOfDay == Integer.parseInt(second.split(":")[0]) && minute > Integer.parseInt(second.split(":")[1]))) {
            Toast.makeText(getApplicationContext(),
                    "Failed! Can't choose later time",
                    Toast.LENGTH_LONG).show();
            return;
        }

        MyTime time = new MyTime(timeList.get(position).getMonth(),
                timeList.get(position).getDay(), hourOfDay, minute);
        appointment.setTime(time);
        databasePatientAppointment.setValue(appointment);
        Toast.makeText(getApplicationContext(),
                "Successfully book the appointment!",
                Toast.LENGTH_LONG).show();
    }

}
