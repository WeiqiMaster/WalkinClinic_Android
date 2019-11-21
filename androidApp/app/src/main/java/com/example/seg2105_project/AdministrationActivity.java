package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdministrationActivity extends AppCompatActivity implements DialogChangeService.DialogChangeServiceListener, View.OnClickListener{
    ListView mListView;
    EditText etServiceName;
    EditText etRoleOfPerson;
    Button btnAddService;
    ArrayList<Service> serviceList;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        mListView = (ListView) findViewById(R.id.listView);
        etServiceName = (EditText) findViewById(R.id.etServiceName);
        etRoleOfPerson = (EditText) findViewById(R.id.etRoleOfPerson);
        btnAddService = (Button) findViewById(R.id.btnAddService);

        btnAddService.setOnClickListener(this);

        Service service1 = new Service("clean", "nurse");
        Service service2 = new Service("clean2", "nurse");

        serviceList = new ArrayList<>();
        serviceList.add(service1);
        serviceList.add(service2);

        ServiceListAdapter adapter = new ServiceListAdapter(this,  R.layout.adapter_view_layout, serviceList, this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogChangeService dialogChangeService = new DialogChangeService();
                dialogChangeService.setPosition(position);
                dialogChangeService.show(getSupportFragmentManager(), "Modify Services");
            }
        });

    }

    @Override
    public void applyText(int position, String serviceName, String roleOfPerson) {
        if (serviceName == null) {
            serviceList.remove(position);
            ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList,this);
            mListView.setAdapter(adapter);
            return;
        }
        for (Service s : serviceList) {
            if (s.getName().equals(serviceName)) {
                Toast.makeText(getApplicationContext(),
                        "Modifying Service Failed!\nService Name already exists.",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        serviceList.set(position, new Service(serviceName, roleOfPerson));
        ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList,this);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddService:
                String serviceName = etServiceName.getText().toString().trim();
                String roleOfPerson = etRoleOfPerson.getText().toString().trim();
                if (serviceName.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Adding Service Failed!\nService Name can not be empty.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                for (Service s : serviceList) {
                    if (s.getName().equals(serviceName)) {
                        Toast.makeText(getApplicationContext(),
                                "Adding Service Failed!\nService Name already exists.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if (!(roleOfPerson.equals("nurse") || roleOfPerson.equals("doctor") || roleOfPerson.equals("staff"))) {
                    Toast.makeText(getApplicationContext(),
                            "Adding Service Failed!\nRole of person has to be \"nurse\", \"doctor\" or \"staff\".",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Service service = new Service(serviceName, roleOfPerson);
                serviceList.add(service);
                ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList,this);
                mListView.setAdapter(adapter);

                reff = FirebaseDatabase.getInstance().getReference().child("Service");
                reff.child(service.getName()).setValue(service);

                break;
        }
    }
}
