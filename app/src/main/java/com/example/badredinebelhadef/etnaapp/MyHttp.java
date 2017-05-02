package com.example.badredinebelhadef.etnaapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by badredinebelhadef on 26/01/2017.
 */

public class MyHttp{

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected OkHttpClient client;

    public MyHttp(){
        this.client = new OkHttpClient();
    }

    public Request post(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return request;
    }

    public String get(String url, String tokenKey, String tokenValue) throws IOException {

        Request request = new Request.Builder()
                .addHeader("Cookie", tokenKey + "=" +tokenValue)
                .url(url)
                .build();

        try ( Response response = client.newCall(request).execute() ) {
            /*System.out.println(" SUCCESS result request Http GET :: " + response.body().string());*/
            return response.body().string();

        } catch (IOException e) {
/*            System.out.println(e.toString() + " Error request Http :: Get Method in MyHttp "); */
            return " Error request Http :: Get Method in MyHttp ";
        }
    }


    public Bitmap getUserImage(String url, String tokenKey, String tokenValue) throws Exception {

        Request request = new Request.Builder()
                .addHeader("Cookie", tokenKey + "=" +tokenValue)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("ERROR Get User Image :: " + response);

        InputStream inputStream = response.body().byteStream();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        return bitmap;
    }



}
