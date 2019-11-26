package com.example.seg2105_project;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import com.example.seg2105_project.activities.AdministrationActivity;

import static org.junit.Assert.*;

public class AdministrationActivityTest {
    @Rule
    public ActivityTestRule<AdministrationActivity> myActivityTestRule = new ActivityTestRule(AdministrationActivity.class);
    private AdministrationActivity myActivity = null;
    //private TextView text;
    private EditText text;

    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkName() throws Exception {
        assertNotNull(myActivity.findViewById(R.id.etServiceName));
        text = myActivity.findViewById(R.id.etServiceName);
        text.setText("service1");
        String name = text.getText().toString();
        assertNotEquals("Name", name);
    }


    @Test
    @UiThreadTest
    public void checkRoleOfPerson() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.etRoleOfPerson));
        text = myActivity.findViewById(R.id.etRoleOfPerson);
        text.setText("nurse");
        String name = text.getText().toString();
        assertNotEquals("Name",name);
    }
}