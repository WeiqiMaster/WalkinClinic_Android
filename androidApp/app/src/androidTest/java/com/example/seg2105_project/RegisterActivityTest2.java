package com.example.seg2105_project;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RegisterActivityTest2 {
    @Rule
    public ActivityTestRule<RegisterActivity> myActivityTestRule = new ActivityTestRule(RegisterActivity.class);
    private RegisterActivity myAcitivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        myAcitivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest

    public void cheakEmail() throws Exception{
        assertNotNull(myAcitivity.findViewById(R.id.Name));
        text = myAcitivity.findViewById(R.id.etName);
        text.setText("admin");
        String name = text.getText().toString();
        assertNotEquals("Name",name);
    }
}
