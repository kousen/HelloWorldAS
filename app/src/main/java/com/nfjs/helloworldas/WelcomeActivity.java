package com.nfjs.helloworldas;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WelcomeActivity extends Activity implements NameFragment.Rateable {
    public static final int NOTIFICATION_ID = 314159;

    private TextView greetingText;
    private ListView listView;
    private Map<String, Integer> ratings = new HashMap<>();

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
        notifyUser(name);

        listView = findViewById(R.id.list_view);
        new DisplayNamesTask().execute(name);

    }

    private class DisplayNamesTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "users.db").build();

            String name = params[0];

            UserDAO userDao = db.getUserDao();
            if (!userDao.exists(name)) {
                userDao.insertUsers(new User(name));
            }

            List<User> users = userDao.getAllUsers();
            List<String> names = new ArrayList<>();
            for (User user : users) {
                names.add(user.getName());
            }

            db.close();

            return names;
        }

        @Override
        protected void onPostExecute(List<String> names) {
            super.onPostExecute(names);
            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<>(
                    WelcomeActivity.this,
                    android.R.layout.simple_list_item_1,
                    names);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Log.d("TAG", "Item at " + position + " clicked");
                  String name = parent.getItemAtPosition(position).toString();
                  greetingText.setText(
                          String.format(getString(R.string.greeting),
                                  name));

                  DialogFragment fragment = new NameFragment();
                  Bundle arguments = new Bundle();
                  arguments.putString("name", name);
                  fragment.setArguments(arguments);
                  fragment.show(getFragmentManager(), "Nothing");
                }
            });
        }
    }

    private void notifyUser(String name) {
        Intent intent = new Intent(this, MyActivity.class);
        TaskStackBuilder tsb = TaskStackBuilder.create(this);
        tsb.addParentStack(MyActivity.class);
        tsb.addNextIntent(intent);
        PendingIntent pendingIntent =
                tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Greeted: " + name)
                .setAutoCancel(true)
                .setTicker("Greeted: " + name)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);
    }

    @Override
    public void modifyRating(String name, int amount) {
        if (ratings.get(name) != null) {
            ratings.put(name, ratings.get(name) + amount);
        } else {
            ratings.put(name, amount);
        }
        Toast.makeText(this, String.format("%s has rating %d", name, ratings.get(name)),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", greetingText.getText().toString());

        String[] names = ratings.keySet().toArray(new String[ratings.keySet().size()]);
        outState.putStringArray("names", names);
        for (String name : names) {
            outState.putInt(name, ratings.get(name));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        greetingText.setText(savedInstanceState.getString("display"));
        String[] names = savedInstanceState.getStringArray("names");
        for (String name : names) {
            ratings.put(name, savedInstanceState.getInt(name));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
