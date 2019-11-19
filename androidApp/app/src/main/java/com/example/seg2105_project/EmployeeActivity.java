package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        tvAddress = findViewById(R.id.tvAddress);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvCompany = findViewById(R.id.tvCompany);
        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ModifyEmplProfileActivity.class);
        intent.putExtra("address", tvAddress.getText().toString().trim());
        intent.putExtra("phoneNumber", tvPhoneNumber.getText().toString().trim());
        intent.putExtra("company", tvCompany.getText().toString().trim());
        startActivity(intent);
    }
}
