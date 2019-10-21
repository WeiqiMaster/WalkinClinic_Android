package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.NoSuchAlgorithmException;

import static com.example.seg2105_project.Hash_256.getSHA;
import static com.example.seg2105_project.Hash_256.toHexString;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etEmail, etPassword;
    TextView tvRegisterLink;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(this);
        bLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                try {
                    password = toHexString(getSHA(password));
                } catch (NoSuchAlgorithmException e) {

                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Could not log in",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
        }
    }
}
