package com.example.badredinebelhadef.etnaapp;

import android.content.Intent;
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
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;



// Interface callbaack
interface AsyncResponse {
    void processFinish(String output);
}


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {






    protected TextView UserCompleteName;

    protected String userBasicInfoUrl = "https://auth.etna-alternance.net/users/";


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



        // repris depuis l'ancien Home
        Intent i = getIntent();

        String tokenKey = i.getStringExtra("tokenKey");
        String tokenValue = i.getStringExtra("tokenValue");
        String login = i.getStringExtra("login");

        System.out.println( "token => " + tokenKey);
        System.out.println( "value => " + tokenValue);


        this.userBasicInfoUrl += login;

        System.out.println( "Url before call Api => " + userBasicInfoUrl);

        HomeGetHttpTask HomeGetHttpTaskObj = new HomeGetHttpTask("https://prepintra-api.etna-alternance.net/users/belhad_b", tokenKey, tokenValue);

        HomeGetHttpTaskObj.delegate = (AsyncResponse) this;

        HomeGetHttpTaskObj.execute();





/*        try {
            JSONObject jobject = new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.

        System.out.println( "LES POTES A LA POTE A LA PETER => " + output);
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


    // Execution in background
    public class HomeGetHttpTask extends AsyncTask<Void, Void, String> {

        protected String url = "";
        protected String tokenKey = "";
        protected String tokenValue = "";

        public AsyncResponse delegate = null;

        HomeGetHttpTask(String urlQuery, String tokenKey, String tokenValue) {
            this.url = urlQuery;
            this.tokenKey = tokenKey;
            this.tokenValue = tokenValue;
        }

        @Override
        protected String doInBackground(Void... params) {

            MyHttp clientHttp = new MyHttp();

            String response = null;
            try {
                response = clientHttp.get(this.url, this.tokenKey, this.tokenValue);
                return response;

            } catch (IOException e) {
                e.printStackTrace();
                return "Error in doInBackground";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            delegate.processFinish(result);
        }

    }
}
