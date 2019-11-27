package com.example.seg2105_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.seg2105_project.DialogEditAvailability;
import com.example.seg2105_project.DialogEmployeeAddService;
import com.example.seg2105_project.R;
import com.example.seg2105_project.TimePickerFragment;
import com.example.seg2105_project.adapters.TimeListAdapter;
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
import java.util.Calendar;

public class ClinicActivity extends AppCompatActivity implements View.OnClickListener, DialogEmployeeAddService.DialogEmployeeServiceListener {
    public final String NODE_NAME_SERVICE = "ServiceForDemo";
    TextView tvWaitingPeople;
    TextView tvClinicName;
    //Button btnBookAppointment;
    Button btnCheckIn;
    ListView listViewWorkingHours;

    DatabaseReference databaseClinic;
    DatabaseReference databasePatient;

    ArrayList<Service> serviceList;
    ArrayList<String> serviceNameList;
    ArrayList<MyTime> timeList;
    Calendar date;
    int month;
    int day;

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
        //btnBookAppointment =findViewById(R.id.btnBookAppointment);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        listViewWorkingHours = findViewById(R.id.listViewWorkingHours);
        timeList = new ArrayList<>();
        //listViewWorkingHours.setAdapter();

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = fbUser.getEmail().replace(".", "");
        databasePatient = FirebaseDatabase.getInstance().getReference().child("Patient").child(email);
        databaseClinic = FirebaseDatabase.getInstance().getReference().child("Employee");
        final String clinicName = getIntent().getStringExtra("clinicName");


        databaseClinic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.child("");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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
                    }
                }

                serviceList = new ArrayList<>();
                serviceNameList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.child("serviceList").getChildren()) {
                    serviceList.add(snapshot.getValue(Service.class));
                    serviceNameList.add(snapshot.child("name").getValue().toString());
                }
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (snapshot.child("name").getValue().toString()
//                            .equals() {
//                        Employee clinic = snapshot.getValue(Employee.class);
//                        tvWaitingPeople.setText(clinic.getWaitingPeople());
//                        tvClinicName.setText(clinic.getName());
//                        //tvWaitingPeople.setText(snapshot.child("waitingPeople").getValue().toString());
//                    }
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        TimeListAdapter adapter = new TimeListAdapter(this,  R.layout.adapter_view_layout, timeList, this);
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

                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();

//                DialogEditAvailability dialogEditAvailability = new DialogEditAvailability();
//                dialogEditAvailability.setPosition(position);
//                dialogEditAvailability.show(getSupportFragmentManager(),"Update Availability");
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

    @Override
    public void applyResult(boolean[] checkedItems) {

    }

    public class DialogBookAppointment {

    }
}