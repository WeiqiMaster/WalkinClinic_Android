package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.seg2105_project.RegisterActivity.maxId;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvWelcome;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        final String email = fbUser.getEmail();


        mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            //User user = snapshot.getValue(User.class);
                            if (snapshot.child("email").getValue().toString().equals(email)) {
                                String welcomMsg = "Welcome " + snapshot.child("name").getValue().toString()
                                        + "! You are logged-in as " + snapshot.child("role").getValue().toString();
                                tvWelcome.setText(welcomMsg);
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

//        for (int i = 1; i <= maxId + 1; i++) {
//            DatabaseReference reff = mDatabase.child(String.valueOf(i));
//            reff.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.child("email").getValue().toString().equals(email)) {
//                        String welcomMsg = "Welcome " + dataSnapshot.child("name").getValue().toString()
//                                + "! You are logged-in as " + dataSnapshot.child("role").getValue().toString();
//                        tvWelcome.setText(welcomMsg);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }
    }
}
