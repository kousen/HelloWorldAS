package com.nfjs.helloworldas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MyActivityLayoutTest
        extends ActivityInstrumentationTestCase2<MyActivity> {

    private MyActivity activity;
    private TextView textView;
    private EditText editText;
    private Button helloButton;
    private Button hiButton;

    public MyActivityLayoutTest() {
        super(MyActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();

        textView = (TextView) activity.findViewById(R.id.text_view);
        editText = (EditText) activity.findViewById(R.id.edit_text);
        helloButton = (Button) activity.findViewById(R.id.hello_button);
        hiButton = (Button) activity.findViewById(R.id.hi_button);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
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
