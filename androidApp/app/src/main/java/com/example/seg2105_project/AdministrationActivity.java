package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AdministrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        ListView mListView = (ListView) findViewById(R.id.listView);
        EditText ed

        Service service1 = new Service("clean", "nurse");
        Service service2 = new Service("clean2", "nurse");

        ArrayList<Service> serviceList = new ArrayList<>();
        serviceList.add(service1);
        serviceList.add(service2);

        ServiceListAdapter adapter = new ServiceListAdapter(this,  R.layout.adapter_view_layout, serviceList);
        mListView.setAdapter(adapter);
    }
}
