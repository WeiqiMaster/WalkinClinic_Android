package com.example.seg2105_project;

import android.widget.TextView;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import androidx.test.rule.ActivityTestRule;
import static org.junit.Assert.*;
import androidx.test.annotation.UiThreadTest;


public class ServiceTest {
    @Rule
    public ActivityTestRule<MainActivity> myseract = new ActivityTestRule(MainActivity.class);
    private  MainActivity serviceact = null;
    private TextView text;


    @Before
    public void setUp() {
        serviceact = myseract.getActivity();
    }


    @Test
    @UiThreadTest

    public void checkName() {
        assertNotNull(serviceact.findViewById(R.id.etServiceName));
        text = serviceact.findViewById(R.id.etServiceName);
        text.setText("Name1");
        String name = text.getText().toString();
        assertNotEquals("Name", name);
    }

}