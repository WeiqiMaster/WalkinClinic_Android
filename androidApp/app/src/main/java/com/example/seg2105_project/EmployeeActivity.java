package com.example.seg2105_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener, DialogEmployeeAddService.DialogEmployeeServiceListener {
    Button btnEdit;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvCompany;
    TextView tvLicensed;
    TextView tvDescription;

    ListView mListView;
    EditText etServiceName;
    EditText etRoleOfPerson;
    Button btnAddService;
    ArrayList<Service> serviceList;

    ArrayList<String> services;
    DatabaseReference mDatabase;
    boolean[] checkedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        tvAddress = findViewById(R.id.tvAddress);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvCompany = findViewById(R.id.tvCompany);
        tvLicensed = findViewById(R.id.tvLicensed);
        tvDescription = findViewById(R.id.tvDescription);
        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listView);
        etServiceName = (EditText) findViewById(R.id.etServiceName);
        etRoleOfPerson = (EditText) findViewById(R.id.etRoleOfPerson);
        btnAddService = (Button) findViewById(R.id.btnAddService);

        btnAddService.setOnClickListener(this);


        serviceList = new ArrayList<>();
//        Service service1 = new Service("clean", "nurse");
//        Service service2 = new Service("clean2", "nurse");
//        serviceList.add(service1);
//        serviceList.add(service2);

        ServiceListAdapter adapter = new ServiceListAdapter(this,  R.layout.adapter_view_layout, serviceList, this);
        mListView.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Service");
        services = new ArrayList<String>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //User user = snapshot.getValue(User.class);
                    String s = snapshot.child("name").getValue().toString()
                            + "      " + snapshot.child("roleOfPerson").getValue().toString();
                    services.add(s);
                }
                checkedItems = new boolean[services.size()];
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
//        Toast.makeText(getApplicationContext(),
//                Integer.toString(services.size()),
//                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
                Intent intent = new Intent(this, ModifyEmplProfileActivity.class);
                intent.putExtra("address", tvAddress.getText().toString().trim());
                intent.putExtra("phoneNumber", tvPhoneNumber.getText().toString().trim());
                intent.putExtra("company", tvCompany.getText().toString().trim());
                intent.putExtra("licensed", tvLicensed.getText().toString().trim().equals("Yes"));
                intent.putExtra("description", tvDescription.getText().toString().trim());
                startActivityForResult(intent, 1);
                break;
            case R.id.btnAddService:
//                Toast.makeText(getApplicationContext(),
//                        Integer.toString(services.size()),
//                        Toast.LENGTH_LONG).show();
                //checkedItems = new boolean[services.size()];
                String[] ss = services.toArray(new String[services.size()]);

                DialogEmployeeAddService dialogUpdateServices = new DialogEmployeeAddService();
                dialogUpdateServices.passValues(ss, checkedItems, services, serviceList);
                dialogUpdateServices.show(getSupportFragmentManager(), "Choose Services");

//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Choose Services");
//                builder.setMultiChoiceItems(ss, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        checkedItems[which] = isChecked;
//                    }
//                });
//
//                // add OK and Cancel buttons
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for (int i = 0; i < services.size(); i++) {
//                            if (checkedItems[i]) {
//                                String[] temp = services.get(i).split("     ", 2);
//                                Service service = new Service(temp[0], temp[1]);
//                                serviceList.add(service);
//                                ServiceListAdapter adapter = new ServiceListAdapter(EmployeeActivity.this,  R.layout.adapter_view_checked, serviceList, this);
//                                mListView.setAdapter(adapter);
//                            }
//                        }
//                    }
//                });
//                builder.setNegativeButton("Cancel", null);
//
//                // create and show the alert dialog
//                AlertDialog dialog = builder.create();
//                dialog.show();

                break;
        }
    }

    @Override
    public void applyResult(boolean[] checkedItems) {
        this.checkedItems = checkedItems;
        serviceList = new ArrayList<Service>();
        for (int i = 0; i < services.size(); i++) {
            if (checkedItems[i]) {
                //isAllFalse = false;
                String[] temp = services.get(i).split("      ", 2);
                Service service = new Service(temp[0], temp[1]);
                serviceList.add(service);
            }
        }
        ServiceListAdapter adapter = new ServiceListAdapter(this,  R.layout.adapter_view_checked, serviceList, this);
        mListView.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String address = data.getStringExtra("address");
                String phoneNumber = data.getStringExtra("phoneNumber");
                String company = data.getStringExtra("company");
                tvAddress.setText(address);
                tvPhoneNumber.setText(phoneNumber);
                tvCompany.setText(company);
                String licensed = data.getExtras().getBoolean("licensed", false) ? "Yes" : "No";
                tvLicensed.setText(licensed);
                tvDescription.setText(data.getStringExtra(("description")));
            }
        }
    }
}
