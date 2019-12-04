package com.example.seg2105_project;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import com.example.seg2105_project.activities.SearchClinicActivity;

import static org.junit.Assert.*;

public class SearchClinicActivityTest {
    @Rule
    public ActivityTestRule<SearchClinicActivity> myActivityTestRule = new ActivityTestRule(SearchClinicActivity.class);
    private SearchClinicActivity myActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkSearch() throws Exception {
        assertNotNull(myActivity.findViewById(R.id.svClinicSearch));
        text = myActivity.findViewById(R.id.svClinicSearch);
        text.setText("XXhospital");
        String name = text.getText().toString();
        assertNotEquals("Search", name);
    }
}