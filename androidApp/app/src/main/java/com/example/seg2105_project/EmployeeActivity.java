package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit;
    TextView tvAddress;
    TextView tvPhoneNumber;
    TextView tvCompany;
    TextView tvLicensed;
    TextView tvDescription;

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

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ModifyEmplProfileActivity.class);
        intent.putExtra("address", tvAddress.getText().toString().trim());
        intent.putExtra("phoneNumber", tvPhoneNumber.getText().toString().trim());
        intent.putExtra("company", tvCompany.getText().toString().trim());
        intent.putExtra("licensed", tvLicensed.getText().toString().trim().equals("Yes"));
        intent.putExtra("description", tvDescription.getText().toString().trim());
        startActivityForResult(intent, 1);
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
