package com.example.badredinebelhadef.etnaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by badredinebelhadef on 16/04/2017.
 */

public class HomeActivity extends AppCompatActivity {

    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = (TextView) findViewById(R.id.textViewTest);

        Intent i = getIntent();

        String fName = i.getStringExtra("tokenKey");
        String lName = i.getStringExtra("tokenValue");

        System.out.println( "token => " + fName);
        System.out.println( "value => " + lName);

        //textView.setText(fName);

    };


}
