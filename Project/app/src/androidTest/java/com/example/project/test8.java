package com.example.project;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class test8 {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(inscrire_client.class);
    private MainActivity mActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.password));
        text = mActivity.findViewById(R.id.password);
        text.setText("password1");
        String name = text.getText().toString();
        assertNotEquals("password", name);
    }
}
