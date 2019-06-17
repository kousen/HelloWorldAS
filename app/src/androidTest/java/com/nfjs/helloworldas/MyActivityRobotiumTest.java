package com.nfjs.helloworldas;

import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Rule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class MyActivityRobotiumTest {

    @Rule
    public ActivityTestRule<MyActivity> rule = new ActivityTestRule<>(MyActivity.class);

    private Solo solo;

    public void setUp() {
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    public void testMyActivity() {
        solo.assertCurrentActivity("MyActivity", MyActivity.class);
    }

    public void testSayHello() {
        solo.enterText(0, "Dolly");
        solo.clickOnButton(rule.getActivity().getString(R.string.hello_button_label));
        solo.assertCurrentActivity("WelcomeActivity", WelcomeActivity.class);
        solo.searchText("Hello, Dolly!");
    }

    public void tearDown() {
        solo.finishOpenedActivities();
    }

}
