package com.example.seg2105_project;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import com.example.seg2105_project.activities.ModifyEmplProfileActivity;

import static org.junit.Assert.*;

public class ModifyEmplProfileActivityTest {
    @Rule
    public ActivityTestRule<ModifyEmplProfileActivity> myActivityTestRule = new ActivityTestRule(ModifyEmplProfileActivity.class);
    private ModifyEmplProfileActivity myActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.etAddress));
        text = myActivity.findViewById(R.id.etAddress);
        text.setText("xxxxxxx");
        String name = text.getText().toString();
        assertNotEquals("Address",name);
    }

    @Test
    @UiThreadTest
    public void checkCompany() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.etCompany));
        text = myActivity.findViewById(R.id.etCompany);
        text.setText("xxxxxxx");
        String name = text.getText().toString();
        assertNotEquals("Company",name);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNumber() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.etPhoneNumber));
        text = myActivity.findViewById(R.id.etPhoneNumber);
        text.setText("123456789");
        String name = text.getText().toString();
        assertNotEquals("PhoneNumber",name);
    }
}