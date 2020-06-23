package com.example.cs4550su1acabeyprojectserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.persistence.*;
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
        this(e.get("id").getAsInt(),
                e.get("seriesName").getAsString(),
                e.get("overview").getAsString(),
                e.get("poster").getAsString(),
                e.get("network").getAsString(),
                e.get("firstAired").getAsString());
    }

}
