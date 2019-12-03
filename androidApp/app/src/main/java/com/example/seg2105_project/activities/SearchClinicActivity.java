package com.example.seg2105_project.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seg2105_project.objects.Employee;
import com.example.seg2105_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchClinicActivity extends AppCompatActivity {
    ListView listViewSearch;
    SearchView svClinicSearch;
    //ArrayList<Employee> employeeList;
    ArrayList<String> clinicNameList;
    ArrayAdapter<String> listAdapter;
    DatabaseReference databaseReferenceClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clinic);

        listViewSearch = findViewById(R.id.listViewSearch);
        svClinicSearch = findViewById(R.id.svClinicSearch);
        clinicNameList = new ArrayList<>();
        databaseReferenceClinics = FirebaseDatabase.getInstance().getReference().child("Employee");
        databaseReferenceClinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Employee employee = snapshot.getValue(Employee.class);
                    clinicNameList.add(snapshot.child("name").getValue().toString());
                }
                listAdapter = new ClinicListAdapter(getApplicationContext(), R.layout.adapter_one_column_listview,
                        clinicNameList);
                listViewSearch.setAdapter(listAdapter);
                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), ClinicActivity.class);
                        intent.putExtra("clinicName", listAdapter.getItem(position));
                        startActivityForResult(intent, 1);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        svClinicSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(clinicNameList.contains(query)){
                    listAdapter.getFilter().filter(query);
                }else{
                    //Toast.makeText(SearchClinicActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    public class ClinicListAdapter extends ArrayAdapter<String> {
        private Context mContext;
        int mResource;
        private TextView tvClinicName;

        public ClinicListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String name = getItem(position);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            tvClinicName = (TextView) convertView.findViewById(R.id.textView1);
            tvClinicName.setText(name);

            return convertView;
        }

    }
}
