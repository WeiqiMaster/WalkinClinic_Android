package com.example.seg2105_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seg2105_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.example.seg2105_project.Hash_256.getSHA;
import static com.example.seg2105_project.Hash_256.toHexString;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etEmail, etPassword;
    TextView tvRegisterLink;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(this);
        bLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
//        DatabaseReference databaseReferenceClinics = FirebaseDatabase.getInstance().getReference().child("Employee");
//        ArrayList<Integer> test = new ArrayList<Integer>();
//        test.add(1);
//        test.add(3);
//        test.add(8);
//        databaseReferenceClinics.child("idk").setValue(test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.equals("admin") && password.equals("123")) {
                    startActivity(new Intent(MainActivity.this, AdministrationActivity.class));
                    break;
                }

                try {
                    password = toHexString(getSHA(password));
                } catch (NoSuchAlgorithmException e) {
                    Toast.makeText(getApplicationContext(),
                            "Unexpected Error when hashing password",
                            Toast.LENGTH_LONG).show();
                }

                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "You must enter an Email",
                            Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Please enter a valid email");
                    etEmail.requestFocus();
                } else if (password.length() < 6 ) {
                    Toast.makeText(getApplicationContext(),
                            "Password has to be at least 6 characters",
                            Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
                                final String email = fbUser.getEmail();

                                mDatabase = FirebaseDatabase.getInstance().getReference();



                                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snapshot : dataSnapshot.child("Employee").getChildren()) {
                                            //User user = snapshot.getValue(User.class);
                                            if (snapshot.child("email").getValue().toString().equals(email)) {
                                                startActivity(new Intent(MainActivity.this, EmployeeActivity.class));
                                                return;
                                            }
                                        }

                                        for (DataSnapshot snapshot : dataSnapshot.child("Patient").getChildren()) {
                                            //User user = snapshot.getValue(User.class);
                                            if (snapshot.child("email").getValue().toString().equals(email)) {
                                                startActivity(new Intent(MainActivity.this, SearchClinicActivity.class));
                                                return;
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Could not log in",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;

        }
    }
}
