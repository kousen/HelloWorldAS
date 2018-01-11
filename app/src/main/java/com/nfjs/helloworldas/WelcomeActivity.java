package com.nfjs.helloworldas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class WelcomeActivity extends Activity {
    private TextView greetingText;
    private DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String name = getIntent().getStringExtra("name");
        greetingText = findViewById(R.id.greeting_text);
        String format = getString(R.string.greeting);
        greetingText.setText(String.format(format, name));

        adapter = new DatabaseAdapter(this);
        adapter.open();
        if (!adapter.exists(name)) {
            adapter.insertName(name);
        }

        ArrayList<User> currentUsers = adapter.getAllNames();
        ListView listView = findViewById(R.id.list_view);

        UserAdapter userAdapter = new UserAdapter(this, currentUsers);

        listView.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", greetingText.getText().toString());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        greetingText.setText(savedInstanceState.getString("display"));
    }
}
