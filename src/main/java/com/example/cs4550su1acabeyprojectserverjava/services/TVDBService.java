package com.example.cs4550su1acabeyprojectserverjava.services;


import com.example.cs4550su1acabeyprojectserverjava.models.Medium;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

@Service
public class TVDBService {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String imageHost = "https://thetvdb.com";
    private static final String apiHost = "https://api.thetvdb.com";

    OkHttpClient client = new OkHttpClient();

    Map<String, String> credentials = new HashMap<>() {{
        put("apikey", "e5094420c444a38c3b46f926de91dde3");
        put("userkey", "5ED01E4096DD78.63651277");
        put("username", "acabey");
    }};

    public TVDBService() {
        /*
        return fetch(`${this.url}/login`, {
            method: 'POST',
            body: JSON.stringify(this.credentials),
            headers: {
                'content-type': 'application/json'
            }
        }).then((response) => {
            return response.json()
        }).then((tokenResponse) => {
            this.token = tokenResponse['token']
        });
         */
        //String loginResponse = this.post();
    }

    InputStream getPosterImage(Integer mediumId, boolean thumbnail) throws IOException {

        String path = "/banners/posters/" + mediumId + "-1" + (thumbnail ? "_t" : "") + ",jpg";

        URIBuilder uri = new URIBuilder().setHost(imageHost).setPath(path);
        Request request = new Request.Builder()
                .url(uri.toString())
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().byteStream();
        }
    }

    public InputStream getPosterImage(Integer mediumId) throws IOException {
        return this.getPosterImage(mediumId, false);
    }

    public InputStream getPosterThumbnail(Integer mediumId) throws IOException {
        return this.getPosterImage(mediumId, true);
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().toString();
        }
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().toString();
        }
    }

    void login() {

    }


    public List<Medium> searchForSeries(String name) {
        return new ArrayList<Medium>();
    }


}
