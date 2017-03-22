package com.example.badredinebelhadef.etnaapp;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by badredinebelhadef on 26/01/2017.
 */

public class PostHttp{

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String sendPost(String url, String json) throws IOException {
        PostHttp example = new PostHttp();
        String response = example.post(url, json);
        System.out.println(response);
        return response;
    }

}
