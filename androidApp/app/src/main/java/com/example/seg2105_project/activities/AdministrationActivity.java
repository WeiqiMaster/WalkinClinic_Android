package com.example.seg2105_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seg2105_project.DialogChangeService;
import com.example.seg2105_project.R;
import com.example.seg2105_project.objects.Service;
import com.example.seg2105_project.adapters.ServiceListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdministrationActivity extends AppCompatActivity implements DialogChangeService.DialogChangeServiceListener, View.OnClickListener{
    public final String NODE_NAME_SERVICE = "ServiceForDemo";
    ListView mListView;
    EditText etServiceName;
    EditText etRoleOfPerson;
    Button btnAddService;
    ArrayList<Service> serviceList;
    DatabaseReference reff;

    Integer maxId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        mListView = (ListView) findViewById(R.id.listView);
        etServiceName = (EditText) findViewById(R.id.etServiceName);
        etRoleOfPerson = (EditText) findViewById(R.id.etRoleOfPerson);
        btnAddService = (Button) findViewById(R.id.btnAddService);
        btnAddService.setOnClickListener(this);
        maxId = 0;

        reff = FirebaseDatabase.getInstance().getReference().child(NODE_NAME_SERVICE);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    serviceList.add(service);
                    maxId = service.getIndex() + 1;
                }
                ServiceListAdapter adapter = new ServiceListAdapter(getApplicationContext(),  R.layout.adapter_view_layout, serviceList);
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void applyText(final int position, String serviceName, String roleOfPerson) {
        if (serviceName == null) {
            reff.child(Integer.toString(serviceList.get(position).getIndex())).removeValue();
//            serviceList.remove(position);
//            ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList);
//            mListView.setAdapter(adapter);
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

        reff.child(Integer.toString(serviceList.get(position).getIndex())).setValue(new Service(serviceName, roleOfPerson));
//        serviceList.set(position, new Service(serviceName, roleOfPerson));
//        ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList);
//        mListView.setAdapter(adapter);
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
                service.setIndex(maxId);
//                serviceList.add(service);
//                ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.adapter_view_layout, serviceList);
//                mListView.setAdapter(adapter);

                //reff = FirebaseDatabase.getInstance().getReference().child(NODE_NAME_SERVICE);
                reff.child(maxId.toString()).setValue(service);
                maxId++;

                break;
        }
    }
}
