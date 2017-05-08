package com.example.badredinebelhadef.etnaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    final static String REQUEST_CURRENTACTIVITIES = "currentactivities";

    protected ImageView userImageView;
    protected TextView userCompleteName;
    protected TextView userEmail;

    protected TextView compagnieName;
    protected ProgressBar progressBarlog;
    protected TextView infoDayLog;
    protected TextView startRun;
    protected TextView endRun;

    protected String userBasicInfoUrl = "https://auth.etna-alternance.net/users/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mListView = (ListView) findViewById(R.id.listViewActivites);


        // repris depuis l'ancien Home
        Intent i = getIntent();
        final String tokenKey = i.getStringExtra("tokenKey");
        final String tokenValue = i.getStringExtra("tokenValue");
        final String login = i.getStringExtra("login");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Retard de " + login);
                intent.putExtra(Intent.EXTRA_TEXT, "Bonjour,\n\n" +
                        "Je suis désolé je vais avoir un peu de retard ce matin,\n\n" +
                        "Cordialement,  ceci est un test EtnApp\n\n" +
                        login);
                intent.setData(Uri.parse("mailto:etna-pedago@etna-alternance.net"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

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
        this.infoDayLog = (TextView) findViewById(R.id.infoDayLog);
        this.startRun = (TextView) findViewById(R.id.startRun);
        this.endRun = (TextView) findViewById(R.id.endRun);
        this.progressBarlog = (ProgressBar) findViewById(R.id.progressBarLog);


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

        HomeGetHttpTask HomeGetCurrentactivities = new HomeGetHttpTask();
        HomeGetCurrentactivities.delegate = (AsyncResponse) this;
        HomeGetCurrentactivities.execute("https://modules-api.etna-alternance.net/students/belhad_b/currentactivities", tokenKey, tokenValue, REQUEST_CURRENTACTIVITIES);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    // @TODO Methode executer dans un autre process donc a revoir ... @badr
    @Override
    public void processFinish(String output, String infoQuery) {
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.

        Gson gson = null;

        Date DateDayNow =  new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.FRANCE);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmtFr = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.FRANCE);
        SimpleDateFormat hourMinFmt = new SimpleDateFormat("hh:mm", Locale.FRANCE);

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

                gson = new Gson();
                DataUserLogs dataUserLogs = gson.fromJson(output, DataUserLogs.class);

                // Show compagnie name if user has contract
                if (dataUserLogs.getContracts().get(0).getCompanyName() != null) {
                    this.compagnieName.setText(dataUserLogs.getContracts().get(0).getCompanyName());
                } else {
                    this.compagnieName.setText("Vous n'avez pas de contrat en cours.");
                }

                try {
                    Date dateStart = formatter.parse(dataUserLogs.getContracts().get(0).getPeriods().get(0).getStart());
                    Date dateEnd = formatter.parse(dataUserLogs.getContracts().get(0).getPeriods().get(0).getEnd());

                    String[] dateStartStr = dateStart.toString().split(" ");
                    String[] dateEndStr = dateEnd.toString().split(" ");
                    // Show Start and end of current Run
                    this.startRun.setText(dateStartStr[2] + " " + dateStartStr[1]);
                    this.endRun.setText(dateEndStr[2] + " " + dateEndStr[1]);

                    Integer log = dataUserLogs.getContracts().get(0).getPeriods().get(0).getLog();
                    Integer log_minutes = dataUserLogs.getContracts().get(0).getPeriods().get(0).getLogMinutes();
                    Integer resultFait = ( 100 * ((log * 60) + log_minutes) ) / 1980;
                    // set progression in progressBar
                    this.progressBarlog.setProgress(resultFait);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Get list of shedules for time of log connexion
                int sizeListShedules = dataUserLogs.getContracts().get(0).getSchedules().size();
                if ( sizeListShedules > 0 ) {

                    boolean hasConnectionToday = false;

                    int i = 0;
                    while (i < sizeListShedules) {

                        Date checkIsToday = null;
                        try {
                            checkIsToday = fmt.parse(dataUserLogs.getContracts().get(0).getSchedules().get(i).getStart());
                            // On check si on du temps de logs aujourd'hui avec la date du jour
                            if (fmt.format(DateDayNow).equals(fmt.format(checkIsToday)))  {

                                Date startLog = null;
                                Date endLog = null;

                                try {
                                    startLog = formatter.parse(dataUserLogs.getContracts().get(0).getSchedules().get(i).getStart());
                                    endLog   = formatter.parse(dataUserLogs.getContracts().get(0).getSchedules().get(i).getEnd());

                                    SpannableString strSlog =  new SpannableString(hourMinFmt.format(startLog));
                                    SpannableString strElog =  new SpannableString(hourMinFmt.format(endLog));
                                    strSlog.setSpan(new StyleSpan(Typeface.BOLD), 0, strSlog.length(), 0);
                                    strElog.setSpan(new StyleSpan(Typeface.BOLD), 0, strElog.length(), 0);

                                    if (i == 0 ) {
                                        this.infoDayLog.append("Aujourd'hui votre temps de travail est pris en compte de : \n\n");
                                    }
                                    this.infoDayLog.append(" - ");
                                    this.infoDayLog.append(strSlog);
                                    this.infoDayLog.append(" à " );
                                    this.infoDayLog.append(strElog);
                                    this.infoDayLog.append("\n");

                                    hasConnectionToday = true;

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ++i;
                    }
                    // If time log connexion not found today
                    if (!hasConnectionToday) {
                        this.infoDayLog.append("Vous n'avez pas de temps de log à faire aujourd'hui \n\n");
                    }
                }
                break;

            case REQUEST_CURRENTACTIVITIES:

                JSONObject jsonObjectData = null;

                try {
                    jsonObjectData = new JSONObject(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String strModule   = "test";
                String strProject  = "toto";
                String strDeadLine = "titi";
                String strDL = null;

                JSONArray modules = jsonObjectData.names();
                // Si on a bien des modules encours ...
                if ( modules.length() > 0 ) {

                    List<Activite> activites = new ArrayList<Activite>();

                    Date deadLine = null;

                    int i;
                    for (i = 0; i < modules.length(); ++i) {
                        try {
                            JSONObject newJSON;
                            newJSON = jsonObjectData.getJSONObject(modules.get(i).toString());

                            strModule = modules.get(i).toString();

                            gson = new Gson();
                            DataJsonProject dataJsonProject = gson.fromJson(newJSON.toString(), DataJsonProject.class);

                            if (dataJsonProject.getProject().size() > 0) {
                                strProject = dataJsonProject.getProject().get(0).getName();
                                strDeadLine = dataJsonProject.getProject().get(0).getDateEnd();
                            }
                            else if (dataJsonProject.getQuest().size() > 0) {
                                strProject = dataJsonProject.getQuest().get(0).getName();
                                strDeadLine = dataJsonProject.getQuest().get(0).getDateEnd();
                            }
                            else {
                                // @TODO Sinon c'est un cours on ne les affiches pas pour le moment @badr
                                continue;
                            }

                            try {
                                deadLine = formatter.parse(strDeadLine);

                                String hourDL = fmtFr.format(deadLine);
                                String minSecDL = hourMinFmt.format(deadLine);

                                strDL = "fin le " + hourDL + " à " + minSecDL;

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            activites.add(new Activite(strModule, strProject, strDL));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ActiviteAdaptater adapter = new ActiviteAdaptater(HomeActivity.this, activites);
                    mListView.setAdapter(adapter);

                }

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
            System.out.println("button : nav_manage"); // appel de la methode en lui passant le token et la value
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
