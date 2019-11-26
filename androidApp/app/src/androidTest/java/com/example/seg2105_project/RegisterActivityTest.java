package com.example.seg2105_project;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import com.example.seg2105_project.activities.RegisterActivity;

import static org.junit.Assert.*;

public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> myActivityTestRule = new ActivityTestRule(RegisterActivity.class);
    private RegisterActivity myActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkName() throws Exception {
        assertNotNull(myActivity.findViewById(R.id.Name));
        text = myActivity.findViewById(R.id.etName);
        text.setText("admin");
        String name = text.getText().toString();
        assertNotEquals("Name", name);
    }


    @Test
    @UiThreadTest
    public void checkEmail() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.etEmail));
        text = myActivity.findViewById(R.id.etEmail);
        text.setText("16465662");
        String name = text.getText().toString();
        assertNotEquals("Name",name);
    }
}