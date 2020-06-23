package com.example.cs4550su1acabeyprojectserverjava.services;


import com.example.cs4550su1acabeyprojectserverjava.models.Medium;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

@Service
public class TVDBService {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String imageHost = "https://thetvdb.com";
    private static final String apiHost = "https://api.thetvdb.com";
    private String token = "";

    OkHttpClient client = new OkHttpClient();

    Map<String, String> credentials = new HashMap<String, String>() {{
        put("apikey", "e5094420c444a38c3b46f926de91dde3");
        put("userkey", "5ED01E4096DD78.63651277");
        put("username", "acabey");
    }};

    public TVDBService() {
        this.login();
    }

    InputStream getPosterImage(Integer mediumId, boolean thumbnail) throws IOException {

        String path = "/banners/posters/" + mediumId + "-1" + (thumbnail ? "_t" : "") + ".jpg";
        String url = imageHost + path;
        Request request = new Request.Builder()
                .url(url)
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

    Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", "Bearer " + this.token)
                .build();
        return client.newCall(request).execute();
    }

    Response get(String url) throws IOException, URISyntaxException {
        return this.get(url, new ArrayList<NameValuePair>());
    }

    Response get(String url, List<NameValuePair> params) throws IOException, URISyntaxException {

        String newUrl = new URIBuilder(url).addParameters(params).build().toString();

        Request request = new Request.Builder()
                .url(newUrl)
                .get()
                .header("Authorization", "Bearer " + this.token)
                .build();
        return client.newCall(request).execute();
    }

    void login() {
        String loginUrl = apiHost + "/login";

        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(credentials,gsonType);

        try {
            Response response = this.post(loginUrl, gsonString);

            if (response.isSuccessful()) {

                String responseBodyString = response.body().string();
                JsonObject jsonObject = new JsonParser().parse(responseBodyString).getAsJsonObject();

                String token = jsonObject.get("token").getAsString();
                this.token = token;
            } else {
                throw new RuntimeException("Login request failed. Response unsuccessful");
            }


        } catch (IOException e) {
            System.out.println("Failed to login to API!");
        }
    }


    public List<Medium> searchForSeries(String name) {

        String searchUrl = apiHost + "/search/series";

        List<NameValuePair> searchParams = new ArrayList<NameValuePair>() {{
            add(new BasicNameValuePair("name", name));
        }};

        try {

            Response response = this.get(searchUrl, searchParams);

            if (response.isSuccessful()) {
                String responseBodyString = response.body().string();

                JsonObject jsonObject = new JsonParser().parse(responseBodyString).getAsJsonObject();

                JsonArray data = jsonObject.getAsJsonArray("data");

                List<Medium> retMedia = new ArrayList<Medium>();

                for (JsonElement jsonMedium : data) {
                    retMedia.add(new Medium(jsonMedium.getAsJsonObject()));
                }

                return retMedia;
            } else {
                throw new RuntimeException("Search request not successful");
            }


        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to search for media in TVDB service");
        }

    }


}
