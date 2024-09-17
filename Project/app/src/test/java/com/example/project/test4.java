package com.example.project;

import org.junit.Rule;
import org.junit.Test;

public class test4 {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(inscrire_client.class);
    @Test
    public void emailIsInvalid() {
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.firstname)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.state)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.city)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.country)).perform(typeText("e1"), closeSoftKeyboard());
        onView(withId(R.id.postalcode)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.streetaddress)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.btn_inscrire_clt2)).perform(click());
        onView(withText("Invalid Email")).check(matches(isDisplayed()));
    }

}
