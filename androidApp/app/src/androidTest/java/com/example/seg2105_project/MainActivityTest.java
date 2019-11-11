package com.example.seg2105_project;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule(MainActivity.class);
    private MainActivity myActivity = null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest

    public void cheakEmail() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.Email));
        text = myActivity.findViewById(R.id.etEmail);
        text.setText("admin");
        String name = text.getText().toString();
        assertNotEquals("Email",name);
    }
    @Test
    @UiThreadTest
    public void cheakPassword() throws Exception{
        assertNotNull(myActivity.findViewById(R.id.Password));
        text = myActivity.findViewById(R.id.etPassword);
        text.setText("300066475");
        String name = text.getText().toString();
        assertNotEquals("Password",name);
    }

}