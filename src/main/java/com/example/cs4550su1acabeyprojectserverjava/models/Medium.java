package com.example.cs4550su1acabeyprojectserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="media")
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tvdbId;
    private String title;
    private String description;
    private String posterUrl;

    @ManyToMany
    @JsonIgnore
    private List<Watchlist> onWatchlists;

}
