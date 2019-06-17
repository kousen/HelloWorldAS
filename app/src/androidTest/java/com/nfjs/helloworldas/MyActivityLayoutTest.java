package com.nfjs.helloworldas;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MyActivityLayoutTest {

    private MyActivity activity;
    private TextView textView;
    private EditText editText;
    private Button helloButton;
    private Button hiButton;

    @Rule
    public ActivityTestRule<MyActivity> rule = new ActivityTestRule<>(MyActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();

        textView = activity.findViewById(R.id.text_view);
        editText = activity.findViewById(R.id.edit_text);
        helloButton = activity.findViewById(R.id.hello_button);
        hiButton = activity.findViewById(R.id.hi_button);
    }

    @Test
    public void testPreconditions() {
        assertNotNull("Activity is null", activity);
        assertNotNull("TextView is null", textView);
        assertNotNull("EditText is null", editText);
        assertNotNull("HelloButton is null", helloButton);
        assertNotNull("HiButton is null", hiButton);
    }

    @Test
    public void textView_label() {
        final String expected = activity.getString(R.string.hello_world);
        final String actual = textView.getText().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void editText_hint() {
        final String expected = activity.getString(R.string.name_hint);
        final String actual = editText.getHint().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void helloButton_label() {
        final String expected = activity.getString(R.string.hello_button_label);
        final String actual = helloButton.getText().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void hiButton_label() {
        final String expected = activity.getString(R.string.hi_button_label);
        final String actual = hiButton.getText().toString();
        assertEquals(expected, actual);
    }
}
