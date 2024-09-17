package com.example.project;

import org.junit.Rule;
import org.junit.Test;

public class test3 {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(cuisinier_sign_in.class);
    @Test
    public void emailIsInvalid() {
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.connecter)).perform(click());
    }

}