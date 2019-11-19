package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModifyEmplProfileActivity extends AppCompatActivity implements View.OnClickListener {
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
        etCompany.setText(getIntent().getStringExtra("company"));

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
    }
}
