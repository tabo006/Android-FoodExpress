package com.example.project;

import org.junit.Rule;
import org.junit.Test;

public class test5 {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(add_dish.class);
    @Test
    public void emailIsInvalid() {
        onView(withId(R.id.name)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.prix)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.typeCuisine)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.rating_repa)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.allergene)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(androidx.appcompat.R.id.add)).perform(click());
        onView(withText("Invalid Email")).check(matches(isDisplayed()));
    }

}
