package com.oreilly.icndb;

import android.os.AsyncTask;
import android.widget.TextView;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class JokeFinder {
    private static final String URL = "http://api.icndb.com/jokes/random?" +
            "limitTo=[nerdy]&firstName={first}&lastName={last}";

    private RestTemplate template = new RestTemplate();
    private AsyncTask<String, Void, String> task;
    private TextView textView;

    public void getJoke(TextView textView, String first, String last) {
        this.textView = textView;
        if (template.getMessageConverters().size() == 0) {
            template.getMessageConverters().add(new GsonHttpMessageConverter());
        }
        task = new JokeTask().execute(first, last);
    }

    private class JokeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            IcndbJoke joke = template.getForObject(URL, IcndbJoke.class,
                    params[0], params[1]);
            return joke.getJoke();
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }
}
