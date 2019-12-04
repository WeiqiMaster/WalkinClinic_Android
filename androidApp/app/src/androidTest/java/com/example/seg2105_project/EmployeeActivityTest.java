package com.example.seg2105_project;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;


import com.example.seg2105_project.activities.EmployeeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeActivityTest {
    @Rule
    public ActivityTestRule<EmployeeActivity> myActivityTestRule = new ActivityTestRule(EmployeeActivity.class);
    private EmployeeActivity myActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.tvAddress));
        text = myActivity.findViewById(R.id.tvAddress);
        text.setText("xxxxxxx");
        String name = text.getText().toString();
        assertNotEquals("Address",name);
    }

    @Test
    @UiThreadTest
    public void checkClinic() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.tvCompany));
        text = myActivity.findViewById(R.id.tvCompany);
        text.setText("xxxxxxx");
        String name = text.getText().toString();
        assertNotEquals("Company",name);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNumber() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.tvPhoneNumber));
        text = myActivity.findViewById(R.id.tvPhoneNumber);
        text.setText("123456789");
        String name = text.getText().toString();
        assertNotEquals("PhoneNumber",name);
    }
    @Test
    @UiThreadTest
    public void checkDescription() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.tvDescription));
        text = myActivity.findViewById(R.id.tvDescription);
        text.setText("xxxxxxxxxxxxxx");
        String name = text.getText().toString();
        assertNotEquals("Description",name);
    }
}