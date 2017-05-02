package com.example.badredinebelhadef.etnaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




// Interface callbaack
interface AsyncResponse {
    void processFinish(String output, String infoQuery);
}

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {

    final static String REQUEST_USER_BASIC_INFO = "basic_info";
    final static String REQUEST_USER_LOG_INTRA = "log_intra";

    protected ImageView userImageView;
    protected TextView userCompleteName;
    protected TextView userEmail;

    protected TextView compagnieName;
    protected ProgressBar progressBarlog;

    protected TextView startRun;
    protected TextView endRun;

    protected String userBasicInfoUrl = "https://auth.etna-alternance.net/users/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
        this.userEmail = (TextView) hView.findViewById(R.id.userEmail);
        this.userCompleteName = (TextView) hView.findViewById(R.id.userCompleteName);
        this.userImageView = (ImageView) hView.findViewById(R.id.userImageView);

        this.compagnieName = (TextView) findViewById(R.id.compagnieName);
        this.startRun = (TextView) findViewById(R.id.startRun);
        this.endRun = (TextView) findViewById(R.id.endRun);

        this.progressBarlog = (ProgressBar) findViewById(R.id.progressBarLog);


        // repris depuis l'ancien Home
        Intent i = getIntent();
        String tokenKey = i.getStringExtra("tokenKey");
        String tokenValue = i.getStringExtra("tokenValue");
        String login = i.getStringExtra("login");

        System.out.println("USER => login :: " + login);

        this.userBasicInfoUrl += login;

        DownloadImageTask DownloadImageTask = new DownloadImageTask(this.userImageView);
        DownloadImageTask.execute("https://auth.etna-alternance.net/api/users/belhad_b/photo", tokenKey, tokenValue);

        HomeGetHttpTask HomeGetUserInfo = new HomeGetHttpTask();
        HomeGetUserInfo.delegate = (AsyncResponse) this;
        HomeGetUserInfo.execute("https://prepintra-api.etna-alternance.net/users/belhad_b", tokenKey, tokenValue, REQUEST_USER_BASIC_INFO);

        HomeGetHttpTask HomeGetUserLog = new HomeGetHttpTask();
        HomeGetUserLog.delegate = (AsyncResponse) this;
        HomeGetUserLog.execute("https://gsa-api.etna-alternance.net/students/belhad_b/logs", tokenKey, tokenValue, REQUEST_USER_LOG_INTRA);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    //this override the implemented method from asyncTask, get data from postOnExecute Here
    @Override
    public void processFinish(String output, String infoQuery) {
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.

        switch (infoQuery) {

            case REQUEST_USER_BASIC_INFO:
                JSONObject jsonDataUser = null;
                try {
                    jsonDataUser = new JSONObject(output);
                    this.userEmail.setText(jsonDataUser.getString("email"));
                    this.userCompleteName.setText(jsonDataUser.getString("firstname") + ' ' + jsonDataUser.getString("lastname"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case REQUEST_USER_LOG_INTRA:

                Gson gson = new Gson();
                DataUserLogs dataUserLogs = gson.fromJson(output, DataUserLogs.class);

                this.compagnieName.setText(dataUserLogs.getContracts().get(0).getCompanyName());

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date dateStart = formatter.parse(dataUserLogs.getContracts().get(0).getPeriods().get(0).getStart());
                    Date dateEnd = formatter.parse(dataUserLogs.getContracts().get(0).getPeriods().get(0).getEnd());

                    String[] dateStartStr = dateStart.toString().split(" ");
                    String[] dateEndStr = dateEnd.toString().split(" ");

                    this.startRun.setText(dateStartStr[2] + " " + dateStartStr[1]);
                    this.endRun.setText(dateEndStr[2] + " " + dateEndStr[1]);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Integer log = dataUserLogs.getContracts().get(0).getPeriods().get(0).getLog();
                Integer log_minutes = dataUserLogs.getContracts().get(0).getPeriods().get(0).getLogMinutes();
                Integer resultFait = ( 100 * ((log * 60) + log_minutes) ) / 1980;

                this.progressBarlog.setProgress(resultFait);


                break;

            default:
                // Do Nothing
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            System.out.println("button : nav_gallery");
        } else if (id == R.id.nav_slideshow) {
            System.out.println("button : nav_slideshow");
        } else if (id == R.id.nav_manage) {
            System.out.println("button : nav_manage");
        } else if (id == R.id.nav_share) {

            System.out.println("button : nav_share");
        } else if (id == R.id.nav_send) {
            System.out.println("button : nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    // Execution in background
    public class HomeGetHttpTask extends AsyncTask<String, Void, String> {

        public AsyncResponse delegate = null;
        public String queryInfo = null;

        @Override
        protected String doInBackground(String... params) {

            String url = params[0];
            String tokenKey = params[1];
            String tokenValue = params[2];
            // Preciser dans quel requette on est pour traiter en fonction de ce qu'on recoit
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
            delegate.processFinish(result, this.queryInfo);
        }
    }


    // TEST DOWLOAD IMAGE
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... params) {

            Bitmap userImage = null;

            String url = params[0];
            String tokenKey = params[1];
            String tokenValue = params[2];

            MyHttp clientHttp = new MyHttp();

            try {
                userImage = clientHttp.getUserImage(url, tokenKey, tokenValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userImage;
        }

        protected void onPostExecute(Bitmap userImage) {
            bmImage.setImageBitmap(userImage);
        }
    }


}
