package com.example.cs4550su1acabeyprojectserverjava.models;

import java.util.List;

public class Watchlist {

    private Integer id;
    private Integer owner;
    private Boolean isPrivate;
    private List<Integer> followers; // Not sure if needed for many-many
    private List<Integer> media;

}
