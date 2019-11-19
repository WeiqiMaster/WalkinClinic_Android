package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ModifyEmplProfileActivity extends AppCompatActivity {
    Button btnSaveChanges;
    TextView etAddress;
    TextView etPhoneNumber;
    TextView etCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_empl_profile);

        etAddress = findViewById(R.id.etAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etCompany = findViewById(R.id.etCompany);

        etAddress.setText(getIntent().getStringExtra("address"));
        etPhoneNumber.setText(getIntent().getStringExtra("phoneNumber"));
    }
}
