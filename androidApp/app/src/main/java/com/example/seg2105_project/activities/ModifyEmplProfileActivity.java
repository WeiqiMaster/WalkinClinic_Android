package com.example.seg2105_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seg2105_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifyEmplProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSaveChanges;
    TextView etAddress;
    TextView etPhoneNumber;
    TextView etCompany;
    Switch swLicensed;
    EditText etDescription;

    DatabaseReference databaseClinic;

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
        String isCheckedText = getIntent().getStringExtra("licensed");
        if (isCheckedText == null){
            swLicensed.setChecked(false);
        } else {
            swLicensed.setChecked(isCheckedText.equals("Yes"));
        }
        etDescription.setText(getIntent().getStringExtra("description"));

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String address = etAddress.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String company = etCompany.getText().toString().trim();
        if (address.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Address can not be empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (phoneNumber.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Phone Number can not be empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (company.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Company can not be empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = fbUser.getEmail().replace(".", "");
        databaseClinic = FirebaseDatabase.getInstance().getReference().child("Employee").child(email);
        databaseClinic.child("address").setValue(address);
        databaseClinic.child("phoneNumber").setValue(phoneNumber);
        databaseClinic.child("company").setValue(company);
        databaseClinic.child("licensed").setValue(swLicensed.isChecked());
        databaseClinic.child("description").setValue(etDescription.getText().toString().trim());

        Intent intent = new Intent();
        intent.putExtra("address", address);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("company", company);
        intent.putExtra("licensed", swLicensed.isChecked());
        intent.putExtra("description", etDescription.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
