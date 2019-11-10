package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdministrationActivity extends AppCompatActivity implements View.OnClickListener{
    ListView mListView;
    EditText etServiceName;
    EditText etRoleOfPerson;
    Button btnAddService;
    ArrayList<Service> serviceList;

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddService:
                String serviceName = etServiceName.getText().toString().trim();
                String roleOfPerson = etRoleOfPerson.getText().toString().trim();
                if (!(roleOfPerson.equals("nurse") || roleOfPerson.equals("doctor") || roleOfPerson.equals("staff"))) {
                    Toast.makeText(getApplicationContext(),
                            "Role of Person has to be \"nurse\", \"doctor\" or \"staff\",",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Service service = new Service(serviceName, roleOfPerson);
                serviceList.add(service);
                ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList,this);
                mListView.setAdapter(adapter);

                break;
        }
    }
}
