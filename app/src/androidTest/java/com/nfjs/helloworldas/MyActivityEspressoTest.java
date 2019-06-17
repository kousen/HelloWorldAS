package com.nfjs.helloworldas;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MyActivityEspressoTest {

    @Rule
    public ActivityTestRule<MyActivity> mActivityRule = new ActivityTestRule<>(MyActivity.class);

    @Test
    public void testHelloButton() {
        onView(withId(R.id.edit_text))
                .perform(typeText("Dolly"));
        onView(withId(R.id.hello_button))
                .perform(click());
        onView(withId(R.id.greeting_text))
                .check(matches(withText("Hello, Dolly!")));
    }

    @Test
    public void testHiButton() {
        onView(withId(R.id.edit_text))
                .perform(typeText("Dolly"));
        onView(withId(R.id.hi_button))
                .perform(click());
        onView(withId(R.id.greeting_text))
                .check(matches(withText("Hello, Dolly!")));
    }
}
