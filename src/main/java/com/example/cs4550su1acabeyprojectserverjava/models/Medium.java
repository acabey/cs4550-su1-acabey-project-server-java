package com.example.cs4550su1acabeyprojectserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="media")
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tvdbId;
    private String title;
    private String description;
    private String posterUrl;
    private String network;
    private String firstAired;

    @ManyToMany
    @JsonIgnore
    private List<Watchlist> onWatchlists;

    public Medium(Integer tvdbId, String title, String description, String posterUrl, String network, String firstAired) {
        this.tvdbId = tvdbId;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.network = network;
        this.firstAired = firstAired;
    }

    public Medium(JsonObject e) {
        this(e.has("id") && !e.get("id").isJsonNull() ? e.get("id").getAsInt() : 0,
                e.has("seriesName") && !e.get("seriesName").isJsonNull() ? e.get("seriesName").getAsString() : "",
                e.has("overview") && !e.get("overview").isJsonNull() ? e.get("overview").getAsString() : "",
                e.has("poster") && !e.get("poster").isJsonNull() ? e.get("poster").getAsString() : "",
                e.has("network") && !e.get("network").isJsonNull() ? e.get("network").getAsString() : "",
                e.has("firstAired") && !e.get("firstAired").isJsonNull() ? e.get("firstAired").getAsString() : "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(Integer tvdbId) {
        this.tvdbId = tvdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public List<Watchlist> getOnWatchlists() {
        return onWatchlists;
    }

    public void setOnWatchlists(List<Watchlist> onWatchlists) {
        this.onWatchlists = onWatchlists;
    }
}
