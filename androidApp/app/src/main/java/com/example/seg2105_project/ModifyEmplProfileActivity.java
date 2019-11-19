package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class ModifyEmplProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSaveChanges;
    TextView etAddress;
    TextView etPhoneNumber;
    TextView etCompany;
    Switch swLicensed;
    EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_empl_profile);

        etAddress = findViewById(R.id.etAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etCompany = findViewById(R.id.etCompany);
        swLicensed = findViewById(R.id.swLicensed);
        etDescription = findViewById(R.id.etDescription);

        etAddress.setText(getIntent().getStringExtra("address"));
        etPhoneNumber.setText(getIntent().getStringExtra("phoneNumber"));
        etCompany.setText(getIntent().getStringExtra("company"));
        swLicensed.setChecked(getIntent().getExtras().getBoolean("licensed"));
        etDescription.setText(getIntent().getStringExtra("description"));

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("address", etAddress.getText().toString().trim());
        intent.putExtra("phoneNumber", etPhoneNumber.getText().toString().trim());
        intent.putExtra("company", etCompany.getText().toString().trim());
        intent.putExtra("licensed", swLicensed.isChecked());
        intent.putExtra("description", etDescription.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
