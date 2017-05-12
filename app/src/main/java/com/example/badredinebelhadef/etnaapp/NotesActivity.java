package com.example.badredinebelhadef.etnaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class NotesActivity extends AppCompatActivity implements AsyncResponse {

    final static String REQUEST_USER_BASIC_INFO = "basic_info";
    final static String REQUEST_USER_LOG_INTRA = "log_intra";

    protected TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String tokenValue = i.getStringExtra("tokenValue");
        String tokenKey = i.getStringExtra("tokenKey");
        String login = i.getStringExtra("login");

        this.test = (TextView) findViewById(R.id.textView);

        NotesGetHttpTask GetNotes = new NotesGetHttpTask();
        GetNotes.delegate = this;
        GetNotes.execute("https://prepintra-api.etna-alternance.net/terms/107/students/" + login + "/marks", tokenKey, tokenValue, REQUEST_USER_BASIC_INFO);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public class NotesGetHttpTask extends AsyncTask<String, Void, String> {

        public AsyncResponse delegate = null;
        public String queryInfo = null;

        @Override
        protected String doInBackground(String... params) {

            String url = params[0];
            String tokenKey = params[1];
            String tokenValue = params[2];
            this.queryInfo = params[3];

            MyHttp clientHttp = new MyHttp();
            String response = null;
            try {
                response = clientHttp.get(url, tokenKey, tokenValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                delegate.processFinish(result, this.queryInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processFinish(String output, String infoQuery) throws JSONException {
        //ArrayList<String> stringArray = new ArrayList<String>();
        //JSONArray jsonArray = new JSONArray(output);
            try {
                JSONArray array = new JSONArray(output);
                StringBuilder sb = new StringBuilder("");
            //Création du tableau pour boucler
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObj = array.getJSONObject(i);
                    sb.append(jsonObj.getString("uv_name"));
                    sb.append('\n');
                    sb.append("Ma note sur le projet ");
                    sb.append(jsonObj.getString("activity_name"));
                    sb.append(" : ");
                    sb.append(jsonObj.getString("student_mark"));
                    sb.append('\n');
                    sb.append("La moyenne de la classe est : ");
                    sb.append(jsonObj.getString("average"));
                    sb.append('\n');
                    sb.append("La note la plus basse est : ");
                    sb.append(jsonObj.getString("minimal"));
                    sb.append('\n');
                    sb.append("La note la pus élevée est : ");
                    sb.append(jsonObj.getString("maximal"));
                    sb.append('\n');
                    sb.append('\n');
                }
                test.setText(sb);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        //this.test.setText(array.toString());


    }
}
